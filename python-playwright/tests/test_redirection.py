# Created on : Apr 8, 2026, 10:02:52 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_redirection import PageRedirection
from src.utilities.logger import get_logger


class TestRedirection(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestRedirection.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageRedirection instance.")
        self.page_redirection = PageRedirection(page)

    def test_redirection(self):
        self.logger.info("Testing Redirection page.")
        self.logger.debug("Open Redirection page.")
        self.page_redirection.open(self.base_url)

        self.logger.debug("Cliking the link to redirect.")
        self.page_redirection.click_link()

        self.log_step("Verify that the URL contains expected value.")
        self.logger.debug("Verifying if the URL contains expected value.")
        self.assert_text_matching(
            self.page_redirection.get_expected_url(),
            self.page_redirection.get_redirected_url(),
            "The redirected page URL must match expected URL.",
        )
        self.logger.debug("Verified that the URL contains expected value.")

        self.log_step("Verify that the redirected page contains expected Heading.")
        self.logger.debug("Verifying if the redirected page contains expected Heading.")
        self.assert_text_matching(
            "status codes",
            self.page_redirection.get_redirected_heading().lower(),
            "The redirected page heading must contain 'status codes'.",
        )
        self.logger.debug(
            "Verified that the redirected page contains expected Heading."
        )

        self.log_step(
            "Verify that the API request returns required status code of 200."
        )
        self.logger.debug(
            "Verifying if the API request returns required status code of 200."
        )
        self.assert_integer_matching(
            200,
            self.page_redirection.get_status_code("/200"),
            "The request must return 200.",
        )
        self.logger.debug(
            "Verified that the API request returns required status code of 200."
        )

        self.log_step("Verify if the API request returns required status code of 301.")
        self.logger.debug(
            "Verifying if the API request returns required status code of 301."
        )
        self.assert_integer_matching(
            301,
            self.page_redirection.get_status_code("/301"),
            "The request must return 301.",
        )
        self.logger.debug(
            "Verified that the API request returns required status code of 301."
        )

        self.log_step("Verify if the API request returns required status code of 404.")
        self.logger.debug(
            "Verifying if the API request returns required status code of 404."
        )
        self.assert_integer_matching(
            404,
            self.page_redirection.get_status_code("/404"),
            "The request must return 404.",
        )
        self.logger.debug(
            "Verified the API request returns required status code of 404."
        )

        self.log_step("Verify if the API request returns required status code of 500.")
        self.logger.debug(
            "Verifying if the API request returns required status code of 500."
        )
        self.assert_integer_matching(
            500,
            self.page_redirection.get_status_code("/500"),
            "The request must return 500.",
        )
        self.logger.debug(
            "Verified the API request returns required status code of 500."
        )
