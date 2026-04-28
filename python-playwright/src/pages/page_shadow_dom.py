# Created on : Apr 8, 2026, 12:08:18 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageShadowDom(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/shadowdom"
    PARAGRAPH = "span[slot='my-text']"
    LIST = "ul[slot='my-text'] li"

    def open(self, base_url: str):
        self.logger.info(f"Opening Shadow Dom page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_paragraph_text(self) -> str:
        self.logger.info("Getting Text of Paragraph.")
        text = self.get_element_text(self.PARAGRAPH)
        if text is None:
            self.logger.error("'None' data type recieved from Heading of new page.")
            raise ValueError("'None' data type recieved from Heading of new page.")
        return text.strip()

    def get_list_text(self, item_num: int) -> str:
        self.logger.info(f"Getting Text of List on index {item_num}.")
        text = self.get_element_text(self.LIST + f":nth-child({item_num})")
        if text is None:
            self.logger.error("'None' data type recieved from Heading of new page.")
            raise ValueError("'None' data type recieved from Heading of new page.")
        return text.strip()
