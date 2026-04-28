# Created on : Mar 28, 2026, 5:45:20 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}


import math
from pathlib import Path

import allure
from playwright.sync_api import Locator

from src.utilities.logger import get_logger, get_test_logger


class BaseTest:
    logger = get_logger(__name__)

    def setup_test(self, page, config, request):
        self.logger.info(
            "Setting up test with provided Playwright page instance, config, and request."
        )
        self.page = page
        self.config = config
        self.request = request
        self._logger = get_test_logger(request.node.nodeid)
        self.base_url = self.config.get("base_url", "")
        self.logger.debug(f"Test setup complete. Base URL: {self.base_url}")

    def log_step(self, message: str):
        self.logger.info(f"Logging test step: {message}")
        self.logger.debug(f"Attaching test step to Allure report: {message}")
        allure.attach(
            message,
            name="Test Step",
            attachment_type=allure.attachment_type.TEXT,
        )

    def assert_text_not_none(self, text: str | None, message: str) -> bool | None:
        self.logger.info(f"Asserting text: {text}")
        assert text is not None, message

    def assert_text_different(
        self, expected_text: str | None, actual_text: str | None, message: str
    ) -> bool | None:
        self.logger.info(f"Asserting text difference: {expected_text} not in {actual_text}")
        if expected_text is not None and actual_text is not None:
            assert expected_text not in actual_text, message
        else:
            raise ValueError("'None' data type provided.")

    def assert_text_matching(
        self, expected_text: str | None, actual_text: str | None, message: str
    ) -> bool | None:
        self.logger.info(f"Asserting text matches: {expected_text} in {actual_text}")
        if expected_text is not None and actual_text is not None:
            assert expected_text in actual_text, message
        else:
            raise ValueError("'None' data type provided.")

    def assert_integer_matching(
        self, expected_value: int | None, actual_value: int | None, message: str
    ) -> bool | None:
        self.logger.info(
            f"Asserting integer matches: {expected_value} == {actual_value}"
        )
        if expected_value is not None and actual_value is not None:
            assert expected_value == actual_value, message
        else:
            raise ValueError("'None' data type provided.")

    def assert_float_matching(
        self, expected_value: float | None, actual_value: float | None, message: str
    ) -> bool | None:
        self.logger.info(f"Asserting float matches: {expected_value} == {actual_value}")
        if expected_value is not None and actual_value is not None:
            assert math.isclose(expected_value, actual_value, abs_tol=0.01)
        else:
            raise ValueError("'None' data type provided.")

    def assert_visible(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is visible.")
        is_visible = locator.is_visible()
        assert is_visible, message

    def assert_hidden(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is hidden.")
        is_hidden = locator.is_hidden()
        assert is_hidden, message

    def assert_enabled(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is enabled.")
        is_visible = locator.is_enabled()
        assert is_visible, message

    def assert_disabled(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is disabled.")
        is_hidden = not locator.is_enabled()
        assert is_hidden, message

    def assert_checked(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is checked.")
        is_checked = locator.is_checked()
        assert is_checked, message

    def assert_not_checked(self, locator: Locator, message: str) -> bool | None:
        self.logger.info(f"Asserting element: {locator} is unchecked.")
        is_not_checked = not locator.is_checked()
        assert is_not_checked, message

    def assert_size_of_list(
        self, expected_size: int, actual_size: int, message: str
    ) -> bool | None:
        self.logger.info(
            f"Asserting List expected size: {expected_size} to match actual size: {actual_size}"
        )
        assert expected_size == actual_size, message

    def assert_approx_size_of_list(
        self, expected_size: list[int], actual_size: int, message: str
    ) -> bool | None:
        self.logger.info(
            f"Asserting List expected size: {expected_size} to match actual size: {actual_size}"
        )
        assert actual_size in expected_size, message

    def assert_greater(
        self, expected_size: int, actual_size: int, message: str
    ) -> bool | None:
        self.logger.info(
            f"Asserting expected size: {expected_size} to be greater than actual size: {actual_size}"
        )
        assert actual_size > expected_size, message

    def assert_list_different(
        self, old_list: list, new_list: list, message: str
    ) -> bool | None:
        self.logger.info(f"Asserting list do not match: {old_list} != {new_list}")
        if old_list is not None and new_list is not None:
            assert old_list != new_list, message
        else:
            raise ValueError("'None' data type provided.")

    def assert_file_exists(self, path: Path, message: str) -> bool | None:
        self.logger.info(f"Asserting path: {path} exists.")
        assert path.exists(), message
