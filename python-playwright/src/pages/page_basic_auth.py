# Created on : Mar 31, 2026, 10:17:01 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageBasicAuth(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/basic_auth"
    HEADING = "#content .example h3"
    PARAGRAPH = "#content .example p"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Basic Authentication page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_heading_text(self) -> str:
        self.logger.info("Getting page heading")
        text = self.get_element_text(self.HEADING)
        if text is None:
            self.logger.error("'None' data type recieved from heading.")
            raise ValueError("'None' data type recieved from heading.")
        return text

    def get_paragraph_text(self) -> str:
        self.logger.info("Getting page paragraph")
        text = self.get_element_text(self.PARAGRAPH)
        if text is None:
            self.logger.error("'None' data type recieved from paragraph.")
            raise ValueError("'None' data type recieved from paragraph.")
        return text
