# Created on : Apr 6, 2026, 9:00:52 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageJSAlerts(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/javascript_alerts"
    BTN_ALERT = "text=Click for JS Alert"
    BTN_CONFIRM = "text=Click for JS Confirm"
    BTN_PROPMT = "text=Click for JS Prompt"
    RESULT = "#result"

    def open(self, base_url: str):
        self.logger.info(f"Opening JS Alerts page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_message_of_alert(self) -> str:
        self.logger.info("Getting message from the JS alert.")
        text = self.get_ok_dialog_message(lambda: self.click_element(self.BTN_ALERT))
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_ok_message_of_confirm(self) -> str:
        self.logger.info("Getting message from the JS confirm.")
        text = self.get_ok_dialog_message(lambda: self.click_element(self.BTN_CONFIRM))
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_cancel_message_of_confirm(self) -> str:
        self.logger.info("Getting message from the JS confirm.")
        text = self.get_cancel_dialog_message(
            lambda: self.click_element(self.BTN_CONFIRM)
        )
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_ok_message_of_prompt(self) -> str:
        self.logger.info("Getting message from the JS prompt.")
        text = self.get_ok_dialog_message(lambda: self.click_element(self.BTN_PROPMT))
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_cancel_message_of_prompt(self) -> str:
        self.logger.info("Getting message from the JS prompt.")
        text = self.get_cancel_dialog_message(
            lambda: self.click_element(self.BTN_PROPMT)
        )
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.strip()

    def get_result(self) -> str:
        self.logger.info("Returning the Result.")
        text = self.get_element_text(self.RESULT)
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text
