# Created on : Apr 8, 2026, 11:40:28 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_secure_download import PageSecureDownload
from src.utilities.logger import get_logger


class TestSecureDownload(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, authenticated_page, config, request):
        self.logger.info("Setting up test environment for TestSecureDownload.")
        self.setup_test(authenticated_page, config, request)
        self.logger.debug("Creating PageSecureDownload instance.")
        self.page_secure_download = PageSecureDownload(authenticated_page)

    def test_secure_download(self):
        self.logger.info("Testing Secure Download page.")
        self.logger.debug("Open Secure Download page.")
        self.page_secure_download.open(self.base_url)

        self.log_step("Verify that the authenticated page contains expected Heading.")
        self.logger.debug(
            "Verifying if the authenticated page contains expected Heading."
        )
        self.assert_text_matching(
            "file downloader",
            self.page_secure_download.get_redirected_heading().lower(),
            "The authenticated page heading must contain 'file downloader'.",
        )
        self.logger.debug("Verified authenticated page contains expected Heading.")

        self.log_step("Download the 13th file")
        downloaded_file = self.page_secure_download.download_file()

        self.log_step("Verify that the file exists in the target directory.")
        self.logger.debug("Verifying if the file exists in the target directory.")
        self.assert_file_exists(
            downloaded_file, f"Expected downloaded file to exist at {downloaded_file}"
        )
        self.logger.debug(f"Verified downloaded file exists at {downloaded_file}")
