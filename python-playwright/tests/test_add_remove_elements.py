# Created on : Mar 28, 2026, 6:12:04 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_add_remove_elements import PageAddRemoveElements
from src.utilities.logger import get_logger


class TestAddRemoveElements(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestAddRemoveElements.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageAddRemoveElements instance.")
        self.page_add_remove_elements = PageAddRemoveElements(page)

    def test_add_remove_visiblity(self):
        self.logger.info("Testing Add Remove Elements page.")
        self.logger.debug("Opening Add Remove Elements page.")
        self.page_add_remove_elements.open(self.base_url)

        self.log_step("Verify Add Element button is visible by default.")
        self.logger.debug("Verifying if Add Element button is visible by default.")
        self.assert_visible(
            self.page_add_remove_elements.get_add_button(),
            "Add Element button should be visible by default.",
        )
        self.logger.debug("Verified Add Element button is visible by default.")

        self.log_step("Verify Delete Element button is hidden by default.")
        self.logger.debug("Verifying if Delete Element button is hidden by default.")
        self.assert_hidden(
            self.page_add_remove_elements.get_delete_button(),
            "Delete Element button should be hidden by default.",
        )
        self.logger.debug("Verified Delete Element button is hidden by default.")

        self.log_step(
            "Verify Delete Element button is visible after clicking Add button."
        )
        self.logger.debug(
            "Verifying if Delete Element button is visible after clicking Add button."
        )
        self.page_add_remove_elements.click_add_button()
        self.assert_visible(
            self.page_add_remove_elements.get_delete_button(),
            "Delete Element button should be visible after clicking Add button.",
        )
        self.logger.debug(
            "Verified Delete Element button is visible after clicking Add button."
        )

        self.log_step(
            "Verify Delete Element button is hidden after clicking Delete button."
        )
        self.logger.debug(
            "Verifying if Delete Element button is hidden after clicking Delete button."
        )
        self.page_add_remove_elements.click_delete_button()
        self.assert_hidden(
            self.page_add_remove_elements.get_delete_button(),
            "Delete Element button should be hidden after clicking Delete button.",
        )
        self.logger.debug(
            "Verified Delete Element button is hidden after clicking Delete button."
        )
