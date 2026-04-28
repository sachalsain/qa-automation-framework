import pytest

from src.core.base_test import BaseTest
from src.pages.page_file_download import PageFileDownload
from src.utilities.logger import get_logger


class TestFileDownload(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFileDownload.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFileDownload instance.")
        self.page_file_download = PageFileDownload(page)

    def test_page_file_download(self):
        self.logger.info("Testing File Download page.")
        self.logger.debug("Open File Download page.")
        self.page_file_download.open(self.base_url)

        self.log_step("Downloading the 13th file.")
        self.logger.debug("Downloading the 13th file.")
        downloaded_file = self.page_file_download.download_file()

        self.log_step("Verify that the file exists in the target directory.")
        self.logger.debug("Verifying if the file exists in the target directory.")
        self.assert_file_exists(
            downloaded_file, f"Expected downloaded file to exist at {downloaded_file}"
        )
        self.logger.debug(f"Verified that downloaded file exists at {downloaded_file}")
