# Created on : Mar 28, 2026, 6:16:36 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.pages.page_ab_test import PageABTesting
from src.core.base_test import BaseTest
from src.utilities.logger import get_logger


class TestABTesting(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestABTesting.")
        self.setup_test(page, config, request)
        # self.page_ab_testing = PageABTesting(page)

    def test_ab_test_heading_compare_between_browsers(
        self, chromium_page, firefox_page
    ):
        self.logger.info("Open A/B Testing page in Chromium and Firefox")

        self.logger.debug("Creating PageABTesting instances for both browsers")
        chrome_page_ab = PageABTesting(chromium_page)
        firefox_page_ab = PageABTesting(firefox_page)

        self.logger.debug("Opening A/B Testing page in both browsers")
        chrome_page_ab.open(self.base_url)
        firefox_page_ab.open(self.base_url)

        self.logger.debug("Retrieving heading text from both browsers")
        chrome_browser_heading = chrome_page_ab.get_header_text()
        firefox_browser_heading = firefox_page_ab.get_header_text()

        self.logger.debug(
            "Logging heading texts for both browsers to show in allure report"
        )
        self.log_step(f"Chromium heading: {chrome_browser_heading}")
        self.log_step(f"Firefox heading: {firefox_browser_heading}")

        self.log_step("Verify Chromium heading is not empty.")
        self.logger.debug("Verifying if the heading text in chrome browser is not None")
        self.assert_text_not_none(
            chrome_browser_heading, "Chromium heading should not be None"
        )
        self.logger.debug("Verified that Chromium heading is not empty.")

        self.log_step("Verify Firefox heading is not empty.")
        self.logger.debug(
            "Verifying if the heading text in firefox browser is not None"
        )
        self.assert_text_not_none(
            firefox_browser_heading, "Firefox heading should not be None"
        )
        self.logger.debug("Verified that Firefox heading is not empty.")

        self.log_step("Verify both headings must be different.")
        self.logger.debug(
            "Verifying if the heading text in chrome browser is not equal to the heading text in firefox browser"
        )
        self.assert_text_different(
            chrome_browser_heading,
            firefox_browser_heading,
            f"Expected text '{chrome_browser_heading}' not matching actual text '{firefox_browser_heading}'",
        )
        self.logger.debug("Verified both headings are different.")
