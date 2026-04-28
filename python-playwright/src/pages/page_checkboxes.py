# Created on : Apr 1, 2026, 9:44:40 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageCheckboxes(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/checkboxes"
    CHECKBOXES = "form#checkboxes input[type='checkbox']"

    def open(self, base_url: str):
        self.logger.info(f"Opening Checkboxes page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_checkboxes_count(self) -> int:
        self.logger.info("Getting checkbox count on Checkboxes page.")
        return self.get_element_count(self.CHECKBOXES)

    def get_checkbox(self, index: int) -> Locator:
        self.logger.info(f"Returning checkbox at index {index}.")
        return self.get_element(self.CHECKBOXES + f":nth-of-type({index})")

    def toggle_checkbox(self, index: int):
        self.logger.info(f"Toggling checkbox at index {index}.")
        self.click_element(self.CHECKBOXES + f":nth-of-type({index})")
