import os
from pathlib import Path

import allure
import pytest

from configurations.config_reader import ConfigurationReader
from services.api_services import ReqResApiService
from utilities.logger import get_logger

logger = get_logger(__name__)


def pytest_addoption(parser):
    parser.addoption(
        "--env",
        action="store",
        default="dev",
        help="Environment name for tests",
    )


@pytest.fixture(scope="session")
def config(pytestconfig):
    logger.info("Setting Environment variable and loading configuration.")
    env = pytestconfig.getoption("env")
    os.environ["TB_ENV"] = env
    logger.debug(f"ENV set to '{env}'; loading configurations.")
    config_data = ConfigurationReader()
    logger.debug(f"Loaded config: {config_data}")
    return config_data


@pytest.fixture(scope="session")
def api_request_context(playwright, config):
    request_context = playwright.request.new_context(
        base_url=config.get("base_url"),
        extra_http_headers={
            "x-api-key": config.get("api_key"),
            "Content-Type": "application/json",
        },
    )
    yield request_context
    request_context.dispose()


@pytest.fixture
def reqres_api(api_request_context, config):
    return ReqResApiService(
        request=api_request_context,
        base_url=config.get("base_url"),
        api_key=config.get("api_key"),
    )


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    outcome = yield
    report = outcome.get_result()

    if report.when != "call" or not report.failed:
        return

    allure.attach(
        str(report.longrepr),
        name="Failure traceback",
        attachment_type=allure.attachment_type.TEXT,
    )

    config_data = ConfigurationReader()
    worker_id = item.config.workerinput["workerid"] if hasattr(item.config, "workerinput") else "main"
    log_file = Path(str(config_data.get("log_dir", "logs/dev"))) / f"{worker_id}.log"

    if log_file.exists():
        allure.attach.file(
            str(log_file),
            name="Execution log",
            attachment_type=allure.attachment_type.TEXT,
        )

    page = item.funcargs.get("page")
    if page:
        screenshot_dir = Path(str(config_data.get("screenshot_dir", "screenshots/dev")))
        screenshot_dir.mkdir(parents=True, exist_ok=True)
        safe_name = item.nodeid.replace("/", "_").replace("\\", "_").replace("::", "_")
        screenshot_path = screenshot_dir / f"{safe_name}.png"

        page.screenshot(path=str(screenshot_path), full_page=True)
        allure.attach.file(
            str(screenshot_path),
            name="Failure screenshot",
            attachment_type=allure.attachment_type.PNG,
        )
