# Created on : Apr 2, 2026, 11:52:24 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from typing import Literal

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDynamicControls(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/dynamic_controls"
    CHECKBOX = "#checkbox-example input[type='checkbox']"
    BTN_CHECKBOX = "#checkbox-example button[type='button']"
    MSG_CHECKBOX = "#checkbox-example #message"
    LOAD_CHECKBOX = "#checkbox-example #loading"
    TEXTBOX = "#input-example input[type='text']"
    BTN_TEXTBOX = "#input-example button[type='button']"
    MSG_TEXTBOX = "#input-example #message"
    LOAD_TEXTBOX = "#input-example #loading"

    def open(self, base_url: str):
        self.logger.info(f"Opening Dynamic Controls page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def reload(self):
        self.logger.info("Reloading Dynamic Controls page.")
        self.reload_page()

    def get_checkbox(self) -> Locator:
        self.logger.info("Returning the Checkbox selector.")
        return self.get_element(self.CHECKBOX)

    def get_textbox(self) -> Locator:
        self.logger.info("Returning the Textbox selector.")
        return self.get_element(self.TEXTBOX)

    def click_checkbox_btn(
        self, state: Literal["attached", "detached", "hidden", "visible"] = "visible"
    ):
        self.logger.info("Clicking Checkbox button.")
        self.click_element(self.BTN_CHECKBOX)
        self.logger.debug("Waiting for Checkbox action to complete.")
        self.wait_for_visibility_change(self.LOAD_CHECKBOX, state)

    def get_checkbox_msg(self) -> str:
        self.logger.info("Getting Checkbox message.")
        text = self.get_element_text(self.MSG_CHECKBOX)
        if text is None:
            self.logger.error("'None' data type recieved from dropdown.")
            raise ValueError("'None' data type recieved from dropdown.")
        return text.strip()

    def click_textbox_btn(
        self,
        button: Literal["left", "middle", "right"] = "left",
        state: Literal["enabled", "disabled"] = "enabled",
    ):
        self.logger.info("Clicking Textbox button.")
        self.click_element(self.BTN_TEXTBOX, button)
        self.logger.debug("Waiting for Textbox action to complete.")
        self.wait_for_enabled_change(self.TEXTBOX, state)

    def get_textbox_msg(self) -> str:
        self.logger.info("Getting Textbox message.")
        text = self.get_element_text(self.MSG_TEXTBOX)
        if text is None:
            self.logger.error("'None' data type recieved from dropdown.")
            raise ValueError("'None' data type recieved from dropdown.")
        return text.strip()

    def enter_value_in_textbox(self, text: str):
        self.logger.info("Entering value into Textbox.")
        self.fill_value_input(self.TEXTBOX, text)

    def get_textbox_value(self):
        self.logger.info("Getting value from Textbox.")
        return self.get_value_input(self.TEXTBOX)
