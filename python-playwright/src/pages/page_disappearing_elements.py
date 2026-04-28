# Created on : Apr 2, 2026, 9:13:44 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDisappearingElements(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/disappearing_elements"
    MENU = "#content"
    MENU_LIST = "#content ul li"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Disappearing Elements page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_menu(self) -> Locator:
        self.logger.info("Returning the Menu selector.")
        return self.get_element(self.MENU)

    def get_list_count(self) -> int:
        self.logger.info("Getting list item count on Disappearing Elements page.")
        return self.get_element_count(self.MENU_LIST)
