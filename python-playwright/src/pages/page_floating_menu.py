# Created on : Apr 4, 2026, 11:35:01 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageFloatingMenu(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/floating_menu"
    MENU = "#menu"

    def open(self, base_url: str):
        self.logger.info(f"Opening Floating Menu page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_menu(self) -> Locator:
        self.logger.info("Returning the Menu selector.")
        return self.get_element(self.MENU)

    def scroll_down(self):
        self.logger.info("Scrolling down.")
        self.execute_script(self.get_menu(), "element => element.scrollIntoView()")
