# Created on : Apr 8, 2026, 8:27:03 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageKeyPress(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/key_presses"
    INPUT = "#target"
    RESULT = "#result"

    def open(self, base_url: str):
        self.logger.info(f"Opening Key Press page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def click_input(self):
        self.logger.info("Clicking on the input field.")
        self.click_element(self.INPUT)

    def get_result(self):
        self.logger.info("Getting value from the result field.")
        text = self.get_element_text(self.RESULT)
        if text is None:
            self.logger.error("'None' data type recieved from result field.")
            raise ValueError("'None' data type recieved from result field.")
        return text.strip()

    def press_key(self, key: str):
        self.logger.info(f"Pressing key: {key}")
        self.press_key_on_keyboard(key)
