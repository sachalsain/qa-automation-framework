# Created on : Apr 2, 2026, 11:02:49 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from typing import Literal

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDynamicElements(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/dynamic_loading/"
    BTN_START = "#start button"
    LOADING = "#loading"
    MESSAGE = "#finish h4"

    def open(self, base_url: str, page_number: int = 1):
        self.logger.info(f"Opening Dynamic Elements page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}{page_number}")

    def get_start_btn(self) -> Locator:
        self.logger.info("Returning the Start button selector.")
        return self.get_element(self.BTN_START)

    def get_message(self) -> Locator:
        self.logger.info("Returning the Start button selector.")
        return self.get_element(self.MESSAGE)

    def click_start_btn(
        self, state: Literal["attached", "detached", "hidden", "visible"] = "visible"
    ):
        self.logger.info("Clicking Start button.")
        self.click_element(self.BTN_START)
        self.logger.debug("Waiting for Start button action to complete.")
        self.wait_for_visibility_change(self.LOADING, state)
