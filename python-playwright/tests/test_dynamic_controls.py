# Created on : Apr 2, 2026, 11:52:34 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_dynamic_controls import PageDynamicControls
from src.utilities.logger import get_logger


class TestDynamicControls(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDynamicControls.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDynamicControls instance.")
        self.page_dynamic_controls = PageDynamicControls(page)

    def test_page_dynamic_controls(self):
        self.logger.info("Testing Dynamic Controls page.")
        self.logger.debug("Open Dynamic Controls page.")
        self.page_dynamic_controls.open(self.base_url)

        self.log_step("Verify that Checkbox is visible on the page.")
        self.logger.debug("Verifying if Checkbox is visible on the page.")
        self.assert_visible(
            self.page_dynamic_controls.get_checkbox(),
            "Checkbox should be visible on the page.",
        )
        self.logger.debug("Verified that Checkbox is visible on the page.")

        self.log_step("Verify that Textbox is visible on the page.")
        self.logger.debug("Verifying if Textbox is visible on the page.")
        self.assert_visible(
            self.page_dynamic_controls.get_textbox(),
            "Textbox should be visible on the page.",
        )
        self.logger.debug("Verified that Textbox is visible on the page.")

        self.log_step("Verify that checkbox should not be checked by default.")
        self.logger.debug("Verifying if checkbox is not checked by default.")
        self.assert_not_checked(
            self.page_dynamic_controls.get_checkbox(),
            "Checkbox should be unchecked by default.",
        )
        self.logger.debug("Verified that Checkbox is not checked by default.")

        self.log_step("Verify that Textbox is disabled by default.")
        self.logger.debug("Verifying if Textbox is disabled by default.")
        self.assert_disabled(
            self.page_dynamic_controls.get_textbox(),
            "Textbox should be disabled by default.",
        )
        self.logger.debug("Verified that Textbox is disabled by default.")

        self.log_step("Click the Checkbox button to remove the checkbox.")
        self.page_dynamic_controls.click_checkbox_btn("hidden")

        self.log_step("Verify that Checkbox is not visible after removing it.")
        self.logger.debug("Verifying if Checkbox is not visible after removing it.")
        self.assert_hidden(
            self.page_dynamic_controls.get_checkbox(),
            "Checkbox should not be visible after removing it.",
        )
        self.logger.debug("Verified that Checkbox is not visible after removing it.")

        self.log_step("Verify that correct Checkbox message is displayed.")
        self.logger.debug("Verifying if correct Checkbox message is displayed.")
        checkbox_msg = self.page_dynamic_controls.get_checkbox_msg().lower()
        self.assert_text_matching(
            "it's gone!",
            checkbox_msg,
            f"Expected 'It's gone!' but got '{checkbox_msg}'",
        )
        self.logger.debug("Verified that the correct Checkbox message is displayed.")

        self.log_step("Click the Checkbox button to add the checkbox.")
        self.logger.debug("Clicking the Checkbox button to add the checkbox.")
        self.page_dynamic_controls.click_checkbox_btn("hidden")

        self.log_step(
            "Verify that Checkbox is now visible on the page after adding it."
        )
        self.logger.debug(
            "Verifying if Checkbox is now visible on the page after adding it."
        )
        self.assert_visible(
            self.page_dynamic_controls.get_checkbox(),
            "Checkbox should be visible after clicking the add button.",
        )
        self.logger.debug(
            "Verified that Checkbox is now visible on the page after adding it."
        )

        self.log_step("Click the Textbox button to enable the textbox.")
        self.page_dynamic_controls.click_textbox_btn()

        self.log_step(
            "Verify that Textbox is enabled after clicking the Textbox button."
        )
        self.logger.debug(
            "Verifying if Textbox is enabled after clicking the Textbox button."
        )
        self.assert_enabled(
            self.page_dynamic_controls.get_textbox(),
            "Textbox should be enabled after clicking the Textbox button.",
        )
        self.logger.debug(
            "Verified that Textbox is enabled after clicking the Textbox button."
        )

        self.log_step("Verify that correct Textbox message is displayed.")
        self.logger.debug("Verifying if correct Textbox message is displayed.")
        textbox_msg = self.page_dynamic_controls.get_textbox_msg().lower()
        self.assert_text_matching(
            "it's enabled",
            textbox_msg,
            f"Expected 'It's enabled!' but got '{textbox_msg}'",
        )
        self.logger.debug("Verified that the correct Textbox message is displayed.")

        self.log_step("Enter value into Textbox.")
        self.logger.debug("Entering value into Textbox.")
        self.page_dynamic_controls.enter_value_in_textbox("Test")

        self.log_step("Verify that correct text is entered in Textbox.")
        self.logger.debug("Verifying if correct text is entered in Textbox.")
        textbox_msg = self.page_dynamic_controls.get_textbox_value().lower()
        self.assert_text_matching(
            "test",
            textbox_msg,
            f"Expected 'Test' but got '{textbox_msg}'",
        )
        self.logger.debug("Verified that correct value is entered in Textbox.")

        self.log_step("Click the Textbox button to disable the textbox.")
        self.page_dynamic_controls.click_textbox_btn("left", "disabled")

        self.log_step(
            "Verify that Textbox is disabled after clicking the Textbox button."
        )
        self.logger.debug(
            "Verifying if Textbox is disabled after clicking the Textbox button."
        )
        self.assert_disabled(
            self.page_dynamic_controls.get_textbox(),
            "Textbox should be disabled after clicking the Textbox button.",
        )
        self.logger.debug(
            "Verified that Textbox is disabled after clicking the Textbox button."
        )

        self.log_step("Verify that correct Textbox message is displayed.")
        self.logger.debug("Verifying if correct Textbox message is displayed.")
        textbox_msg = self.page_dynamic_controls.get_textbox_msg().lower()
        self.assert_text_matching(
            "it's disabled",
            textbox_msg,
            f"Expected 'It's disabled!' but got '{textbox_msg}'",
        )
        self.logger.debug("Verified that the correct Textbox message is displayed.")
