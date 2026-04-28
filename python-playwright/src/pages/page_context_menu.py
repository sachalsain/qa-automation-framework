# Created on : Apr 1, 2026, 10:18:54 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageContextMenu(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/context_menu"
    HOT_SPOT = "#hot-spot"

    def open(self, base_url: str):
        self.logger.info(f"Opening Context Menu page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_hot_spot(self) -> Locator:
        self.logger.info("Returning the Hot spot selector.")
        return self.get_element(self.HOT_SPOT)

    def get_text_of_alert(self) -> str:
        self.logger.info("Getting text from the context menu alert.")
        text = self.get_ok_dialog_message(
            lambda: self.click_element(self.HOT_SPOT, "right")
        )
        if text is None:
            self.logger.error("'None' data type recieved from alert.")
            raise ValueError("'None' data type recieved from alert.")
        return text
