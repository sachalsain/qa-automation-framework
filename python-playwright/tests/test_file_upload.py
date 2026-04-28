# Created on : Apr 3, 2026, 1:24:32 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_file_upload import PageFileUpload
from src.utilities.logger import get_logger


class TestFileUpload(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFileUpload.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFileUpload instance.")
        self.page_file_upload = PageFileUpload(page)

    def test_page_file_upload(self):
        self.logger.info("Testing File Upload page")
        self.logger.debug("Open File Upload page")
        self.page_file_upload.open(self.base_url)

        self.log_step("Upload the file from uploads folder.")
        self.logger.debug("Uploading the file from uploads folder.")
        self.page_file_upload.upload()

        self.log_step(
            "Verify that file upload completed successfully by checking the success message title."
        )
        self.logger.debug(
            "Verifying if the file upload completed successfully by checking the success message title."
        )
        self.assert_text_matching(
            "file uploaded!",
            self.page_file_upload.get_uploaded_message_title().lower(),
            "The title should contain 'file uploaded!' in it.",
        )
        self.logger.debug("Verified that file upload flow completed by title.")

        self.log_step(
            "Verify that file upload completed successfully by checking the success message text."
        )
        self.logger.debug(
            "Verifying if the file upload completed successfully by checking the success message text."
        )
        self.assert_text_matching(
            "uploadTestFile.txt".lower(),
            self.page_file_upload.get_uploaded_message_text().lower(),
            "Uploaded file name must match 'uploadTestFile.txt'.",
        )
        assert (
            "uploadTestFile.txt"
            == self.page_file_upload.get_uploaded_message_text().strip()
        ), "Uploaded file name must match 'uploadTestFile.txt'."
        self.logger.debug("Verified file upload flow completed by text.")
