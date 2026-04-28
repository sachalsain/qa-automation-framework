# Created on : Apr 4, 2026, 11:44:07 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Response

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageForgotPassword(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/forgot_password"
    IMPUT_EMAIL = "#email"
    BTN_SUBMIT = "#form_submit[type='submit']"
    MESSAGE = "h1"

    def open(self, base_url: str):
        self.logger.info(f"Opening Forgot Password page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_message(self) -> str:
        self.logger.info("Getting page Message")
        text = self.get_element_text(self.MESSAGE)
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_page_url(self) -> str:
        self.logger.info("Returning the URL of the page.")
        return self.PAGE_PATH

    def get_status_code(self, url) -> Response:
        self.logger.info(f"Getting API response for url: '{url}'")
        return self.post_API_response(url, {"email": "test@example.com"})

    def is_form_submitted(self) -> bool:
        self.logger.info(
            "Checking if form is submitted successfully by verifying the message."
        )
        expected_message = "internal server error"
        actual_message = self.get_message()
        self.logger.debug(
            f"Expected message: '{expected_message}', Actual message: '{actual_message}'"
        )
        return actual_message == expected_message

    def submit_form(self):
        self.logger.info("Submitting form.")
        self.click_element(self.BTN_SUBMIT)
