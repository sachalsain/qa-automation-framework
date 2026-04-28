# Created on : Apr 2, 2026, 10:19:44 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_dropdown import PageDropdown
from src.utilities.logger import get_logger


class TestDropdown(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDropdown.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDropdown instance.")
        self.page_dropdown = PageDropdown(page)

    def test_page_dropdown(self):
        self.logger.info("Testing Dropdown page")
        self.logger.debug("Open Dropdown page")
        self.page_dropdown.open(self.base_url)

        self.log_step("Verify that default option is selected.")
        self.logger.debug("Verifying if default option is selected.")
        self.assert_text_matching(
            "select an option",
            self.page_dropdown.get_selected_value().lower(),
            "Default option should be selected",
        )
        self.logger.debug("Verified that default option is selected.")

        self.log_step("Selecting Option 1 from dropdown.")
        self.logger.debug("Selecting Option 1 from dropdown.")
        self.page_dropdown.select_value("Option 1")

        self.log_step("Verify that Option 1 is selected.")
        self.logger.debug("Verifying if Option 1 is selected.")
        self.assert_text_matching(
            "option 1",
            self.page_dropdown.get_selected_value().lower(),
            "'Option 1' should be selected",
        )
        self.logger.debug("Verified that Option 1 is selected.")

        self.log_step("Selecting Option 2 from dropdown.")
        self.logger.debug("Selecting Option 2 from dropdown.")
        self.page_dropdown.select_value("Option 2")

        self.log_step("Verify that Option 2 is selected")
        self.logger.debug("Verifying if Option 2 is selected.")
        self.assert_text_matching(
            "option 2",
            self.page_dropdown.get_selected_value().lower(),
            "'Option 2' should be selected",
        )
        self.logger.debug("Verified that Option 2 is selected.")
