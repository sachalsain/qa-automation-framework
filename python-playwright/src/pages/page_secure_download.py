# Created on : Apr 8, 2026, 11:40:13 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from pathlib import Path

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageSecureDownload(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/download_secure"
    LINK = "#redirect"
    HEADING_NEW_PAGE = "#content .example h3"
    DOWNLOAD_LINK = "#content .example a:nth-of-type(13)"

    def open(self, base_url: str):
        self.logger.info(f"Opening Secure Download page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_redirected_heading(self) -> str:
        self.logger.info("Getting Heading of authenticated page")
        text = self.get_element_text(self.HEADING_NEW_PAGE)
        if text is None:
            self.logger.error("'None' data type recieved from Heading of new page.")
            raise ValueError("'None' data type recieved from Heading of new page.")
        return text.strip()

    def download_file(self) -> Path:
        self.logger.info("Downloading 13th file.")
        download_path = self.click_and_wait_for_download(self.DOWNLOAD_LINK)
        self.logger.debug(f"File downloaded to {download_path}")
        return download_path
