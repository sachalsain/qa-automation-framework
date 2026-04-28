# Created on : Apr 3, 2026, 1:24:24 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger
from src.utilities.path_loader import load_uploads_dir


class PageFileUpload(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/upload"
    BTN_BROWSE = "#file-upload"
    BTN_UPLOAD = "#file-submit"
    SUCCESS_MESSAGE_TITLE = "#content .example h3"
    SUCCESS_MESSAGE_TEXT = "#uploaded-files"

    def open(self, base_url: str):
        self.logger.info(f"Opening File Upload page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def upload(self):
        self.logger.info("Uploading file.")
        self.upload_file(self.BTN_BROWSE, load_uploads_dir() / "uploadTestFile.txt")
        self.click_element(self.BTN_UPLOAD)

    def get_uploaded_message_title(self) -> str:
        self.logger.info("Getting uploaded message Title.")
        text = self.get_element_text(self.SUCCESS_MESSAGE_TITLE)
        if text is None:
            self.logger.error("'None' data type recieved from message Title.")
            raise ValueError("'None' data type recieved from message Title.")
        return text.strip()

    def get_uploaded_message_text(self) -> str:
        self.logger.info("Getting uploaded message Text.")
        text = self.get_element_text(self.SUCCESS_MESSAGE_TEXT)
        if text is None:
            self.logger.error("'None' data type recieved from message Text.")
            raise ValueError("'None' data type recieved from message Text.")
        return text.strip()
