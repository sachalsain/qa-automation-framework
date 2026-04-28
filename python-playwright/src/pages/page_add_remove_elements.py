# Created on : Mar 28, 2026, 6:08:38 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageAddRemoveElements(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/add_remove_elements/"
    BTN_ADD = "button[onclick='addElement()']"
    BTN_DELETE = "button[onclick='deleteElement()']"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Add/Remove Elements page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_add_button(self) -> Locator:
        self.logger.info("Requesting add button element.")
        element = self.get_element(self.BTN_ADD)
        return element

    def get_delete_button(self) -> Locator:
        self.logger.info("Requesting delete button element.")
        element = self.get_element(self.BTN_DELETE)
        return element

    def click_add_button(self):
        self.logger.info("Clicking Add button")
        self.click_element(self.BTN_ADD)

    def click_delete_button(self):
        self.logger.info("Clicking Delete button")
        self.click_element(self.BTN_DELETE)
