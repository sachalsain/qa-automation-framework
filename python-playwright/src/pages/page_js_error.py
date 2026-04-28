# Created on : Apr 8, 2026, 8:07:54 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageJSError(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/javascript_error"

    def __init__(self, page):
        super().__init__(page)
        self.errors = []

    # def open(self, base_url: str):
    #     self.logger.debug(f"Opening JS Error page at {base_url}{self.PAGE_PATH}")
    #     self.navigate(f"{base_url}{self.PAGE_PATH}")

    def open(self, base_url: str):
        self.logger.info(f"Opening JS Error page at {base_url}{self.PAGE_PATH}")
        self.errors.clear()
        self.logger.debug("Waiting for page error during navigation.")
        with self.page.expect_event("pageerror") as error_info:
            self.navigate(f"{base_url}{self.PAGE_PATH}")
        self.errors.append(error_info.value)

    def getErrors(self):
        self.logger.info("Returning Errors.")
        return self.errors
