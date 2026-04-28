# Created on : Apr 4, 2026, 11:35:10 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_floating_menu import PageFloatingMenu
from src.utilities.logger import get_logger


class TestFloatingMenu(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFloatingMenu.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFloatingMenu instance.")
        self.page_floating_menu = PageFloatingMenu(page)

    def test_page_floating_menu(self):
        self.logger.info("Testing Floating Menu page.")
        self.logger.debug("Open Floating Menu page.")
        self.page_floating_menu.open(self.base_url)

        self.log_step("Verify that the Menu is visible.")
        self.logger.debug("Verifying if the Menu is visible.")
        self.assert_visible(
            self.page_floating_menu.get_menu(),
            "Menu should be visible on the page.",
        )
        self.logger.debug("Verified that Menu is visible on the page.")

        self.log_step("Scrolling the page down.")
        self.logger.debug("Scrolling the page down.")
        self.page_floating_menu.scroll_down()

        self.log_step("Verify that the Menu is visible.")
        self.logger.debug("Verifying if the Menu is visible.")
        self.assert_visible(
            self.page_floating_menu.get_menu(),
            "Menu should be visible on the page.",
        )
        self.logger.debug("Verified that Menu is visible on the page.")
