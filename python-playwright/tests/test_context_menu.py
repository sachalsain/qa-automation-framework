# Created on : Apr 1, 2026, 10:19:07 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_context_menu import PageContextMenu
from src.utilities.logger import get_logger


class TestContextMenu(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestContextMenu.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageContextMenu instance.")
        self.page_context_menu = PageContextMenu(page)

    def test_page_context_menu(self):
        self.logger.info("Testing Context Menu page.")
        self.logger.debug("Open Context Menu page.")
        self.page_context_menu.open(self.base_url)

        self.log_step("Verify the hot spot is visible.")
        self.logger.debug("Verifying if the hot spot is visible.")
        self.assert_visible(
            self.page_context_menu.get_hot_spot(), "Hot spot must be visible."
        )
        self.logger.debug("Verified the hot spot is visible.")

        self.log_step("Verify the alert message matches expected value.")
        self.logger.debug(
            "Performing right-click on the hot spot and retrieving the alert message."
        )
        actual_value = self.page_context_menu.get_text_of_alert().lower()
        self.assert_text_matching(
            "context menu",
            actual_value,
            f"Expected 'context menu' but got '{actual_value}'",
        )
        self.logger.debug("Verified that the alert message matches expected value.")
