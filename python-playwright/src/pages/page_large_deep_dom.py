# Created on : Apr 8, 2026, 8:43:23 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageLargeDeepDom(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/large"
    TABLE = "#large-table"

    def open(self, base_url: str):
        self.logger.info(f"Opening Large Deep Dom page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_table(self) -> Locator:
        self.logger.info("Returning the Table selector.")
        return self.get_element(self.TABLE)

    def get_value_of_cell(self, row: int, cell: int):
        self.logger.info("Returning the value of required cell.")
        text = self.get_element_text(f".row-{row} .column-{cell}")
        if text is None:
            self.logger.error("'None' data type recieved from result field.")
            raise ValueError("'None' data type recieved from result field.")
        return text.strip()
