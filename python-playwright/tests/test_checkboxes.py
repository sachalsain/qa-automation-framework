# Created on : Apr 1, 2026, 9:44:55 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_checkboxes import PageCheckboxes
from src.utilities.logger import get_logger


class TestCheckboxes(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestCheckboxes.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageCheckboxes instance.")
        self.page_checkboxes = PageCheckboxes(page)

    def test_page_checkboxes(self):
        self.logger.info("Testing Checkboxes page.")
        self.logger.debug("Open Checkboxes page.")
        self.page_checkboxes.open(self.base_url)

        self.log_step("Verify two checkboxes are present on the page.")
        self.logger.debug("Verifying if two checkboxes are present on the page.")
        actual_value = self.page_checkboxes.get_checkboxes_count()
        self.assert_integer_matching(
            2,
            actual_value,
            f"Page should have two checkboxes but got {actual_value} checkboxes.",
        )
        self.logger.debug("Verified two checkboxes are present on the page.")

        self.log_step("Verify the first checkbox is unchecked by default.")
        self.logger.debug("Verifying if the first checkbox is unchecked by default.")
        self.assert_not_checked(
            self.page_checkboxes.get_checkbox(1),
            "First checkbox should be unchecked by default.",
        )
        self.logger.debug("Verified the first checkbox is unchecked by default.")

        self.log_step("Verify the second checkbox is checked by default.")
        self.logger.debug("Verifying if the second checkbox is checked by default.")
        self.assert_checked(
            self.page_checkboxes.get_checkbox(2),
            "Second checkbox should be checked by default.",
        )
        self.logger.debug("Verified the second checkbox is checked by default.")

        self.log_step(
            "Selecting that first checkbox and checking if it is selected now."
        )
        self.logger.debug("Clicking the first checkbox.")
        self.page_checkboxes.toggle_checkbox(1)

        self.log_step("Verify the first checkbox is checked after clicking.")
        self.logger.debug("Verifying if the first checkbox is checked after clicking.")
        self.assert_checked(
            self.page_checkboxes.get_checkbox(1),
            "First checkbox should be checked after clicking.",
        )
        self.logger.debug("Verified the first checkbox is checked after clicking.")
