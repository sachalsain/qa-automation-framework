# Created on : Apr 4, 2026, 11:44:24 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_forgot_password import PageForgotPassword
from src.utilities.logger import get_logger


class TestForgotPassword(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestForgotPassword.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageForgotPassword instance.")
        self.page_forgot_password = PageForgotPassword(page)

    def test_forgot_password(self):
        self.logger.info("Testing Forgot Password page.")
        self.logger.debug("Open Forgot Password page.")
        self.page_forgot_password.open(self.base_url)

        self.log_step("Submitting Form.")
        self.page_forgot_password.submit_form()

        self.log_step("Verify that Response is 500 Internal Server Error.")
        self.logger.debug("Requesting response.")
        response = self.page_forgot_password.get_status_code(
            self.base_url + self.page_forgot_password.get_page_url()
        )
        self.logger.debug("Verifying if Response is 500 Internal Server Error.")
        self.assert_integer_matching(
            500,
            response.status,
            "The Response should contain 500 Internal Server Error.",
        )
        self.logger.debug("Verified that Response is 500 Internal Server Error.")
