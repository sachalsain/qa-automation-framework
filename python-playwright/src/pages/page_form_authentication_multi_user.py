# Created on : Apr 4, 2026, 11:57:48 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageFormAuthentication(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/login"
    INPUT_USERNAME = "#username"
    INPUT_PASSWORD = "#password"
    BTN_SUBMIT = "#login button[type='submit']"
    MESSAGE = "#flash"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Form Authentication page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def login(self, username, password):
        self.logger.info("Login requested.")
        self.logger.debug("Entering username.")
        self.fill_value_input(self.INPUT_USERNAME, username)
        self.logger.debug("Entering password.")
        self.fill_value_input(self.INPUT_PASSWORD, password)
        self.logger.debug("Clicking login button.")
        self.click_element(self.BTN_SUBMIT)

    def get_message(self) -> str:
        self.logger.info("Getting page Message")
        text = self.get_element_text(self.MESSAGE)
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()
