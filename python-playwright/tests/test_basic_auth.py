# Created on : Mar 31, 2026, 10:17:15 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_basic_auth import PageBasicAuth
from src.utilities.logger import get_logger


class TestBasicAuth(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, authenticated_page, config, request):
        self.logger.info("Setting up test environment for TestBasicAuth.")
        self.setup_test(authenticated_page, config, request)
        self.logger.debug("Creating PageBasicAuth instance.")
        self.page_basic_auth = PageBasicAuth(authenticated_page)

    def test_basic_auth(self):
        self.logger.info("Testing Basic Authentication page.")
        self.logger.debug(
            "Opening Basic Authentication page with authenticated page fixture."
        )
        self.page_basic_auth.open(self.base_url)

        self.log_step(
            "Verify that the heading text contains SUCCESS after successful login."
        )
        self.logger.debug(
            "Verifying if the heading text contains SUCCESS after successful login"
        )
        self.assert_text_matching(
            "basic auth",
            self.page_basic_auth.get_heading_text().lower(),
            "Basic Authentication heading should not be None and must contain 'Basic Auth'",
        )
        self.logger.debug(
            "Verified that the heading text contains SUCCESS after successful login."
        )

        self.log_step(
            "Verify that the paragraph text contains SUCCESS after successful login."
        )
        self.logger.debug(
            "Verifying if the paragraph text contains SUCCESS after successful login"
        )
        self.assert_text_matching(
            "congratulations",
            self.page_basic_auth.get_paragraph_text().lower(),
            "Basic Authentication paragraph should not be None and must contain 'Congratulations'",
        )
        self.logger.debug(
            "Verified that the heading text contains SUCCESS after successful login."
        )
