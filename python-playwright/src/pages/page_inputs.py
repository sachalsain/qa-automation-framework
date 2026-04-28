# Created on : Apr 6, 2026, 8:19:25 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageInputs(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/inputs"
    INPUT = "input[type='number']"

    def open(self, base_url: str):
        self.logger.info(f"Opening Inputs page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def fill_number(self, number: int):
        self.logger.info("Filling value in the input field.")
        self.fill_value_input(self.INPUT, str(number))

    def get_value(self):
        self.logger.info("Getting value from the input field.")
        return self.get_value_input(self.INPUT)
