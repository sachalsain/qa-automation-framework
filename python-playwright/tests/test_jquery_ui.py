# Created on : Apr 6, 2026, 8:26:25 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_jquery_ui import PageJqueryUi
from src.utilities.logger import get_logger


class TestJqueryUi(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestJqueryUi.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageJqueryUi instance.")
        self.page_jquery_ui = PageJqueryUi(page)

    def test_jquery_ui(self):
        self.logger.info("Testing Jquery UI page.")
        self.logger.debug("Open Jquery UI page.")
        self.page_jquery_ui.open(self.base_url)

        self.log_step("Verify that the first menu item is disabled.")
        self.logger.debug("Verifying if the first menu item is disabled.")
        self.assert_disabled(
            self.page_jquery_ui.get_menu_disabled(),
            "The first menu item must be disabled by default.",
        )
        self.logger.debug("Verified that the first menu item is disabled.")

        self.log_step("Verify that the Second menu item is enabled.")
        self.logger.debug("Verifying if the Second menu item is enabled.")
        self.assert_enabled(
            self.page_jquery_ui.get_menu_enabled(),
            "The second menu item must be enabled.",
        )
        self.logger.debug("Verified that Second menu item is enabled.")

        self.logger.debug("Hover over Enabled menu item to show subMenu.")
        self.page_jquery_ui.hover_over_enabled()

        self.log_step(
            "Verify that the Downloads menu item is visible after hovering over the enabled item."
        )
        self.logger.debug(
            "Verifying if the Downloads menu item is visible after hovering over the enabled item."
        )
        self.assert_visible(
            self.page_jquery_ui.get_menu_downloads(),
            "The Downloads menu item must be visible after hovering over the enabled item.",
        )
        self.logger.debug(
            "Verified that the Downloads menu item is visible after hovering over the enabled item."
        )

        self.logger.debug("Hover over Downloads menu item to show subMenu.")
        self.page_jquery_ui.hover_over_downloads()

        self.log_step(
            "Verify that subMenu with item CSV is visible after hovering over the downloads item."
        )
        self.logger.debug(
            "Verifying if subMenu with item CSV is visible after hovering over the downloads item."
        )
        self.assert_visible(
            self.page_jquery_ui.get_menu_downloads(),
            "The CSV menu item must be visible after hovering over the downloads item.",
        )
        self.logger.debug(
            "Verified that subMenu with item CSV is visible after hovering over the downloads item."
        )
