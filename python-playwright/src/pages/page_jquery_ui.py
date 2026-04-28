# Created on : Apr 6, 2026, 8:25:53 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageJqueryUi(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/jqueryui/menu"
    MENU_DISABLED = "text=Disabled"
    MENU_ENABLED = "text=Enabled"
    MENU_DOWNLOADS = "text=Downloads"
    MENU_CSV = "text=CSV"

    def open(self, base_url: str):
        self.logger.info(f"Opening Jquery UI page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_menu_disabled(self) -> Locator:
        self.logger.info("Returning the Disabled Menu selector.")
        return self.get_element(self.MENU_DISABLED)

    def get_menu_enabled(self) -> Locator:
        self.logger.info("Returning the Enabled Menu selector.")
        return self.get_element(self.MENU_ENABLED)

    def hover_over_enabled(self):
        self.logger.info("Hovering over Enabled Menu Item.")
        self.hover_on(self.MENU_ENABLED)
        self.wait_for_visibility_change(self.MENU_DOWNLOADS, "visible")

    def get_menu_downloads(self) -> Locator:
        self.logger.info("Returning the Downloads selector.")
        return self.get_element(self.MENU_DOWNLOADS)

    def hover_over_downloads(self):
        self.logger.info("Hovering over Downloads Menu Item.")
        self.hover_on(self.MENU_DOWNLOADS)
        self.wait_for_visibility_change(self.MENU_CSV, state="visible")

    def get_menu_csv(self) -> Locator:
        self.logger.info("Returning the CSV selector.")
        return self.get_element(self.MENU_CSV)
