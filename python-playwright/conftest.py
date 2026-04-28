# from datetime import datetime
from datetime import datetime
import json
import os
from pathlib import Path


# import allure
import allure
import pytest

from src.configurations.configuration_loader import ConfigurationLoader
from src.utilities.logger import get_logger
from src.utilities.path_loader import load_screenshots_dir
# from src.utilities.path_loader import load_screenshots_dir

logger = get_logger(__name__)


#   Add a custom CLI argument to pytest
def pytest_addoption(parser):
    logger.info("Adding custom CLI options to pytest.")
    parser.addoption(
        "--env",
        action="store",
        default="dev",
        help="Environment to run tests against: dev/stage/prod",
    )


#   Reads --env from CLI, Sets it in environment variables, Loads correct config file (dev.json, stage.json, etc.) for Centralized config access
@pytest.fixture(scope="session")
def config(pytestconfig):
    logger.info("Setting Environment variable and loading configuration.")
    env = pytestconfig.getoption("env")
    os.environ["TB_ENV"] = env
    logger.debug(f"ENV set to '{env}'; loading configurations.")
    config_data = ConfigurationLoader()
    logger.debug(f"Loaded config: {config_data}")
    return config_data


#   Reads browser from config and provides it to tests. Can be extended to read other settings as needed.
# @pytest.fixture(scope="session")
# def browser_name(config):
#     logger.info("Setting browser name from configurations.")
#     browser = config.get("browser", "chromium")
#     logger.debug(f"Using browser from configurations: {browser}")
#     return browser


#   Provides default browser context settings. HTTPS bypass, screen resolution, locale, geolocation, etc
@pytest.fixture(scope="session")
def browser_context_args(config):
    logger.info("Setting browser context arguments.")
    context_args = {
        "ignore_https_errors": config.get("ignore_https_errors", True),
        "accept_downloads": True,
        "viewport": {
            "width": config.get("viewport_width", 1440),
            "height": config.get("viewport_height", 900),
        },
    }
    logger.debug(f"Using browser context arguments: {context_args}")
    return context_args


#   Provides default browser context settings for geolocation.
# @pytest.fixture(scope="session")
# def browser_context_args_geolocation(config):
#     logger.info("Setting browser context arguments for geolocation.")
#     context_args = {
#         "ignore_https_errors": config.get("ignore_https_errors", True),
#         "geolocation": {
#             "latitude": config.get("geolocation_latitude", 31.4),
#             "longitude": config.get("geolocation_longitude", 74.2),
#         },
#         "accept_downloads": True,
#         "viewport": {
#             "width": config.get("viewport_width", 1440),
#             "height": config.get("viewport_height", 900),
#         },
#     }
#     logger.debug(f"Using browser context arguments: {context_args}")
#     return context_args


#   Defines how browser launches. headless or not, slow motion (debugging), etc.
@pytest.fixture(scope="session")
def browser_type_launch_args(config):
    logger.info("Setting browser type launch arguments.")
    launch_args = {
        "headless": config.get("headless", True),
        "slow_mo": config.get("slow_mo", 0),
    }
    logger.debug(f"Using browser type launch arguments: {launch_args}")
    return launch_args


# #   Applies per-test page configuration to the pytest-playwright page fixture
# @pytest.fixture(autouse=True)
# def configure_page(page, config):
#     logger.info("Configuring page fixture with settings from configurations.")
#     timeout = config.get("timeout", 30000)
#     page.set_default_timeout(timeout)
#     logger.debug(f"Applied page timeout: {timeout}")
#     return page


#   Provides a Playwright page fixture with the configured HTTPCredentials context settings.
@pytest.fixture
def authenticated_page(playwright, config):
    logger.info(
        "Setting up authenticated page fixture with HTTP credentials from configurations."
    )
    username = config.get("http_username", "admin")
    password = config.get("http_password", "admin")
    logger.debug(f"Using HTTP credentials from configurations: username='{username}'")
    context = playwright.chromium.launch().new_context(
        http_credentials={"username": username, "password": password},
        ignore_https_errors=config.get("ignore_https_errors", True),
        viewport={
            "width": config.get("viewport_width", 1440),
            "height": config.get("viewport_height", 900),
        },
    )
    page = context.new_page()
    page.set_default_timeout(config.get("timeout", 30000))
    yield page
    context.close()


#   Provides a Playwright page fixture with the configured browser and context settings. Can be extended to support multiple browsers based on config.
@pytest.fixture
def chromium_page(playwright, config):
    logger.info(
        "Setting up Playwright page fixture with the configured browser and context settings."
    )
    logger.debug("Launching Chromium browser with settings from configurations.")
    browser = playwright.chromium.launch(
        headless=config.get("headless", True),
        slow_mo=config.get("slow_mo", 0),
    )
    logger.debug("Creating browser context with settings from configurations.")
    context = browser.new_context(
        ignore_https_errors=config.get("ignore_https_errors", True),
        viewport={
            "width": config.get("viewport_width", 1440),
            "height": config.get("viewport_height", 900),
        },
    )
    logger.debug("Creating new page and applying default timeout from configurations.")
    page = context.new_page()
    page.set_default_timeout(config.get("timeout", 30000))

    logger.debug("Yielding page fixture for test execution.")
    yield page

    logger.debug("Closing browser context after test execution.")
    context.close()
    logger.debug("Closing browser after test execution.")
    browser.close()


#   Similar fixture for Firefox browser. Can be extended to support more browsers (WebKit, etc.) based on config.
@pytest.fixture
def firefox_page(playwright, config):
    logger.info("Setting up Playwright page fixture for Firefox browser.")
    logger.debug("Launching Firefox browser with settings from configurations.")
    browser = playwright.firefox.launch(
        headless=config.get("headless", True),
        slow_mo=config.get("slow_mo", 0),
    )
    logger.debug("Creating browser context with settings from configurations.")
    context = browser.new_context(
        ignore_https_errors=config.get("ignore_https_errors", True),
        viewport={
            "width": config.get("viewport_width", 1440),
            "height": config.get("viewport_height", 900),
        },
    )
    logger.debug("Creating new page and applying default timeout from configurations.")
    page = context.new_page()
    page.set_default_timeout(config.get("timeout", 30000))

    logger.debug("Yielding page fixture for test execution.")
    yield page

    logger.debug("Closing browser context after test execution.")
    context.close()
    logger.debug("Closing browser after test execution.")
    browser.close()


def load_json_data(file_path: Path) -> dict | list:
    with open(file_path, "r", encoding="utf-8") as file:
        return json.load(file)


#   Captures test result at each stage: - setup - call (actual test) - teardown.
@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    logger.info(f"Generating pytest report for {item.name}")
    outcome = yield
    rep = outcome.get_result()
    setattr(item, "rep_" + rep.when, rep)
    logger.debug(f"pytest report for {item.name} ({rep.when}) status={rep.outcome}")


#   Screenshot on failure fixture. autouse=True -> Runs automatically for every test — no need to declare it. Gives visual debugging evidence - Integrates directly into Allure report - Equivalent to: Java → @Attachment
@pytest.fixture(autouse=True)
def attach_screenshot_on_failure(request, page):
    logger.info(f"Setting up screenshot attachment for {request.node.name}")
    logger.debug(
        "Yielding to test execution and will check for failure after test completes."
    )
    yield
    logger.debug(
        "Test execution completed; checking if test failed to capture screenshot."
    )
    if hasattr(request.node, "rep_call") and request.node.rep_call.failed:
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        file_name = f"{request.node.name}_{timestamp}.png"
        file_path = load_screenshots_dir() / file_name

        logger.error(
            f"Test failed: {request.node.name}; saving screenshot to {file_path}"
        )

        logger.debug("Capturing screenshot of the current page state.")
        page.screenshot(path=str(file_path), full_page=True)

        logger.debug("Attaching screenshot to Allure report.")
        allure.attach.file(
            str(file_path),
            name=f"Failure Screenshot - {request.node.name}",
            attachment_type=allure.attachment_type.PNG,
        )
