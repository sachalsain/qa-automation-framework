# Created on : Apr 8, 2026, 9:08:14 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageNewWindow(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/windows"
    NEW_WINDOW_LINK = ".example a"
    NEW_WINDOW_TEXT = "h3"

    def open(self, base_url: str):
        self.logger.info(f"Opening New Window page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def click_to_create_new_page(self):
        self.logger.info("Creating a new page.")
        return self.create_new_window(self.NEW_WINDOW_LINK)

    def get_new_page_text(self, window):
        self.logger.info("Returning the text displayed on new page.")
        text = self.get_window_element_text(window, self.NEW_WINDOW_TEXT)
        if text is None:
            self.logger.error("'None' data type recieved from Heading of new page.")
            raise ValueError("'None' data type recieved from Heading of new page.")
        return text.strip()
