# Created on : Apr 8, 2026, 8:27:22 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_key_press import PageKeyPress
from src.utilities.logger import get_logger


class TestKeyPress(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestKeyPress.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageKeyPress instance.")
        self.page_key_press = PageKeyPress(page)

    def test_key_press(self):
        self.logger.info("Testing Key Press page.")
        self.logger.debug("Open Key Press page.")
        self.page_key_press.open(self.base_url)

        self.logger.debug("Clicking the input field.")
        self.page_key_press.click_input()

        self.logger.debug("Pressing the 'a' key.")
        self.page_key_press.press_key("a")

        self.log_step("Verify that the result diplays 'a' after Pressing key 'a'.")
        self.logger.debug("Verifying if the result diplays 'a' after Pressing key 'a'.")
        self.assert_text_matching(
            ": a",
            self.page_key_press.get_result().lower(),
            "The result should display 'a' after pressing key 'a'.",
        )
        self.logger.debug(
            "Verified that the result diplays 'a' after Pressing key 'a'."
        )

        self.logger.debug("Pressing the 'ArrowDown' key.")
        self.page_key_press.press_key("ArrowDown")

        self.log_step(
            "Verify that the result diplays 'down' after Pressing key 'ArrowDown'."
        )
        self.logger.debug(
            "Verifying if the result diplays 'down' after Pressing key 'ArrowDown'."
        )
        self.assert_text_matching(
            ": down",
            self.page_key_press.get_result().lower(),
            "The result should display 'down' after pressing the 'ArrowDown' key.",
        )
        self.logger.debug(
            "Verified that the result diplays 'down' after Pressing key 'ArrowDown'."
        )
