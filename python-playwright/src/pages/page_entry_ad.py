# Created on : Apr 3, 2026, 11:20:31 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from typing import Literal

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageEntryAd(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/entry_ad"
    AD_WINDOW = "#modal .modal"
    AD_WINDOW_TITLE = "#modal .modal .modal-title h3"
    AD_WINDOW_PARAGRAPH = "#modal .modal .modal-body p"
    AD_WINDOW_CLOSE_BTN = "#modal .modal .modal-footer p"

    def open(self, base_url: str):
        self.logger.info(f"Opening Entry Ad page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def wait_for_ad_window_visible(
        self,
        state: Literal["attached", "detached", "hidden", "visible"] = "visible",
        timeout: int = 30000,
    ):
        self.logger.info("Waiting for Ad Window to load.")
        self.wait_for_visibility_change(self.AD_WINDOW, state, timeout)

    def get_ad_window(self) -> Locator:
        self.logger.info("Returning the Start button selector.")
        return self.get_element(self.AD_WINDOW)

    def get_window_title(self) -> str:
        self.logger.info("Getting Ad Window Title.")
        text = self.get_element_text(self.AD_WINDOW_TITLE)
        if text is None:
            self.logger.error("'None' data type recieved from Ad Window Title.")
            raise ValueError("'None' data type recieved from Ad Window Title.")
        return text.strip()

    def get_window_paragraph(self) -> str:
        self.logger.info("Getting Ad Window Paragraph.")
        text = self.get_element_text(self.AD_WINDOW_PARAGRAPH)
        if text is None:
            self.logger.error("'None' data type recieved from Ad Window Paragraph.")
            raise ValueError("'None' data type recieved from Ad Window Paragraph.")
        return text.strip()

    def get_window_footer(self) -> str:
        self.logger.info("Getting Ad Window Footer.")
        text = self.get_element_text(self.AD_WINDOW_CLOSE_BTN)
        if text is None:
            self.logger.error("'None' data type recieved from Ad Window Footer.")
            raise ValueError("'None' data type recieved from Ad Window Footer.")
        return text.strip()

    def get_window_close_btn(self) -> Locator:
        self.logger.info("Returning the Window Close Button.")
        return self.get_element(self.AD_WINDOW_CLOSE_BTN)

    def click_window_close_btn(
        self,
        button: Literal["left", "middle", "right"] = "left",
        state: Literal["attached", "detached", "hidden", "visible"] = "visible",
    ):
        self.logger.info("Clicking Window Close button.")
        self.click_element(self.AD_WINDOW_CLOSE_BTN, button)
        self.logger.debug("Waiting for Textbox action to complete.")
        self.wait_for_visibility_change(self.AD_WINDOW_CLOSE_BTN, state)

    def reload(self):
        self.logger.info("Reloading Entry Ad page")
        self.reload_page()
