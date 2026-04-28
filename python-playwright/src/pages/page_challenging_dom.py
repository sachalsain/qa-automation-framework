# Created on : Mar 31, 2026, 1:07:38 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageChallengingDOM(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/challenging_dom"
    TABLE = "table"
    TABLE_HEADERS = TABLE + " thead tr th"
    TABLE_DATA_ROWS = TABLE + " tbody tr"

    def open(self, base_url: str):
        self.logger.info(f"Opening Challenging DOM page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_table_headers_count(self) -> int:
        self.logger.info("Getting count of headers.")
        count = self.get_element_count(self.TABLE_HEADERS)
        self.logger.debug(f"Found {count} headers.")
        return count

    def get_table_data_row_count(self) -> int:
        self.logger.info("Getting count of data rows.")
        return self.get_element_count(self.TABLE_DATA_ROWS)

    def get_table_data_value(self, row: int, column: int) -> str:
        self.logger.info(
            f"Getting the value of data cell on row: {row} and column: {column}."
        )
        text = self.get_element_text(
            self.TABLE_DATA_ROWS + f":nth-child({row}) td:nth-child({column})"
        )
        if text is None:
            self.logger.error("'None' data type recieved from table.")
            raise ValueError("'None' data type recieved from table.")
        return text
