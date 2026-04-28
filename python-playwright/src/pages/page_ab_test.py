# Created on : Mar 28, 2026, 6:16:06 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageABTesting(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/abtest"
    HEADER = "h3"
    DESCRIPTION = "p"

    def open(self, base_url: str):
        self.logger.info(f"Opening A/B Testing page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_header_text(self) -> str:
        self.logger.info("Getting heading text")
        text = self.get_element_text(self.HEADER)
        if text is None:
            self.logger.error("'None' data type recieved from heading.")
            raise ValueError("'None' data type recieved from heading.")
        return text

    def get_description_text(self) -> str:
        self.logger.info("Getting description text")
        text = self.get_element_text(self.DESCRIPTION)
        if text is None:
            self.logger.error("'None' data type recieved from description.")
            raise ValueError("'None' data type recieved from description.")
        return text
