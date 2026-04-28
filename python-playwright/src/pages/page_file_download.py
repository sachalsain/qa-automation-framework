# Created on : Apr 3, 2026, 12:34:20 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from pathlib import Path

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageFileDownload(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/download"
    DOWNLOAD_LINK = "#content .example a:nth-of-type(13)"

    def open(self, base_url: str):
        self.logger.info(f"Opening File Download page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def download_file(self) -> Path:
        self.logger.info("Downloading 13th file.")
        download_path = self.click_and_wait_for_download(self.DOWNLOAD_LINK)
        self.logger.debug(f"File downloaded to {download_path}")
        return download_path
