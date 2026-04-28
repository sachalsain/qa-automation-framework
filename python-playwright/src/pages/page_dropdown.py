# Created on : Apr 2, 2026, 10:19:27 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDropdown(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/dropdown"
    DROPDOWN = "#dropdown"

    def open(self, base_url: str):
        self.logger.info(f"Opening Dropdown page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def select_value(self, value: str):
        self.logger.info(f"Selecting value '{value}' from dropdown.")
        self.select_dropdown(self.DROPDOWN, value)

    def get_selected_value(self) -> str:
        self.logger.info("Getting selected value from dropdown.")
        text = self.get_text_selected_dropdown(self.DROPDOWN)
        if text is None:
            self.logger.error("'None' data type recieved from dropdown.")
            raise ValueError("'None' data type recieved from dropdown.")
        return text.strip()
