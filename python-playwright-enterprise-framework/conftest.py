from datetime import datetime
from pathlib import Path

import allure
import pytest
from playwright.sync_api import sync_playwright

from utils.config_reader import get_config_value


@pytest.fixture(scope="session")
def browser_type_name():
    return get_config_value("browser", "name", default="chromium")


@pytest.fixture(scope="session")
def headless_mode():
    return get_config_value("browser", "headless", default=True)


@pytest.fixture(scope="session")
def browser(browser_type_name, headless_mode):
    with sync_playwright() as playwright:
        browser_type = getattr(playwright, browser_type_name)
        browser = browser_type.launch(headless=headless_mode)
        yield browser
        browser.close()


@pytest.fixture
def context(browser):
    viewport_width = get_config_value("browser", "viewport", "width", default=1366)
    viewport_height = get_config_value("browser", "viewport", "height", default=768)
    video_dir = get_config_value("reports", "video_dir", default="reports/videos")
    trace_dir = get_config_value("reports", "trace_dir", default="reports/traces")

    if video_dir:
        Path(video_dir).mkdir(parents=True, exist_ok=True)
    if trace_dir:
        Path(trace_dir).mkdir(parents=True, exist_ok=True)

    context = browser.new_context(
        viewport={"width": viewport_width, "height": viewport_height},
        record_video_dir=video_dir if video_dir else None,
    )

    context.tracing.start(
        screenshots=True,
        snapshots=True,
        sources=True,
    )

    yield context

    if trace_dir:
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        trace_path = Path(trace_dir) / f"trace_{timestamp}.zip"

        context.tracing.stop(path=str(trace_path))

        allure.attach.file(
            str(trace_path),
            name="Playwright Trace",
            attachment_type=allure.attachment_type.ZIP,
        )
    else:
        context.tracing.stop()

    context.close()


@pytest.fixture
def page(context, request):
    timeout = get_config_value("browser", "timeout", default=30000)

    page = context.new_page()
    page.set_default_timeout(timeout)

    yield page

    if request.node.rep_call.failed:
        screenshot_dir = get_config_value(
            "reports",
            "screenshot_dir",
            default="reports/screenshots",
        ) or "reports/screenshots"

        Path(screenshot_dir).mkdir(parents=True, exist_ok=True)

        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        screenshot_path = Path(screenshot_dir) / f"{request.node.name}_{timestamp}.png"

        page.screenshot(path=str(screenshot_path), full_page=True)

        allure.attach.file(
            str(screenshot_path),
            name="Failure Screenshot",
            attachment_type=allure.attachment_type.PNG,
        )


@pytest.fixture(scope="session")
def base_url():
    return get_config_value("app", "base_url")


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item):
    outcome = yield
    report = outcome.get_result()

    setattr(item, f"rep_{report.when}", report)
