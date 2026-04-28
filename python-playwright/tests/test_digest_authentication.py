# Created on : Apr 2, 2026, 8:57:33 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_digest_authentication import PageDigestAuthentication
from src.utilities.logger import get_logger


class TestDigestAuthentication(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, authenticated_page, config, request):
        self.logger.info("Setting up test environment for TestDigestAuthentication.")
        self.setup_test(authenticated_page, config, request)
        self.logger.debug("Creating PageDigestAuthentication instance.")
        self.page_digest_authentication = PageDigestAuthentication(authenticated_page)

    def test_digest_authentication(self):
        self.logger.info("Testing Digest Authentication page.")
        self.logger.debug(
            "Open Digest Authentication page with authenticated page fixture."
        )
        self.page_digest_authentication.open(self.base_url)

        self.log_step("Verify heading text is SUCCESS.")
        self.logger.debug("Verifying if heading text is SUCCESS.")
        msg_heading = self.page_digest_authentication.get_heading_text().lower()
        self.assert_text_matching(
            "digest auth",
            msg_heading,
            f"Digest Authentication heading must contain 'Digest Auth' but found '{msg_heading}'",
        )
        self.logger.debug("Verified that heading text is SUCCESS.")

        self.log_step("Verify paragraph text is SUCCESS.")
        self.logger.debug("Verifying if paragraph text is SUCCESS.")
        msg_paragraph = self.page_digest_authentication.get_paragraph_text().lower()
        self.assert_text_matching(
            "congratulations",
            msg_paragraph,
            f"Digest Authentication heading must contain 'Digest Auth' but found '{msg_paragraph}'",
        )
        self.logger.debug("Verified that paragraph text is SUCCESS.")
