# Created on : Apr 3, 2026, 11:20:47 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_entry_ad import PageEntryAd
from src.utilities.logger import get_logger


class TestEntryAd(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestEntryAd.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageEntryAd instance.")
        self.page_entry_ad = PageEntryAd(page)

    def test_page_entry_ad(self):
        self.logger.info("Testing Entry Ad page.")
        self.logger.debug("Open Entry Ad page.")
        self.page_entry_ad.open(self.base_url)

        self.log_step("Verify that the Ad Window is Visible.")
        self.logger.debug("Waiting for Ad Window to be displayed.")
        self.page_entry_ad.wait_for_ad_window_visible()
        self.logger.debug("Verifying if Ad Window is visible on the page.")
        self.assert_visible(
            self.page_entry_ad.get_ad_window(),
            "Ad window should be visible on the page.",
        )
        self.logger.debug("Verified that Ad window is visible on the page.")

        self.log_step("Verify that proper Ad window Title is displayed.")
        self.logger.debug("Verifying if correct Ad window Title is displayed.")
        title_msg = self.page_entry_ad.get_window_title().lower()
        self.assert_text_matching(
            "this is a modal window",
            title_msg,
            f"Expected 'This is a modal window' but got '{title_msg}'",
        )
        self.logger.debug("Verified that the correct Ad window Title is displayed.")

        self.log_step("Verify that proper Ad window body is displayed.")
        self.logger.debug("Verifying if correct Ad window body is displayed.")
        body_msg = self.page_entry_ad.get_window_paragraph().lower()
        self.assert_text_matching(
            "commonly used to encourage",
            body_msg,
            f"Expected 'commonly used to encourage' but got '{body_msg}'",
        )
        self.logger.debug("Verified that the correct Ad window body is displayed.")

        self.log_step("Verify that proper Ad window footer is displayed.")
        self.logger.debug("Verifying if correct Ad window footer is displayed.")
        body_msg = self.page_entry_ad.get_window_footer().lower()
        self.assert_text_matching(
            "close",
            body_msg,
            f"Expected 'close' but got '{body_msg}'",
        )
        self.logger.debug("Verified that the correct Ad window footer is displayed.")

        self.log_step("Click the Ad Window Button to close the window.")
        self.logger.debug("Clicking the Ad Window Button to close the window.")
        self.page_entry_ad.click_window_close_btn("left", "hidden")

        self.log_step("Verify that the Ad Window is Hidden after clicking close.")
        self.logger.debug("Verifying if Ad Window is Hidden after clicking close.")
        self.assert_hidden(
            self.page_entry_ad.get_ad_window(),
            "Ad window should be Hidden after clicking close.",
        )
        self.logger.debug("Verified that Ad window is Hidden after clicking close.")

        self.log_step("Reloading the page to check if window is displayed again.")
        self.logger.debug("Reloading the page.")
        self.page_entry_ad.reload()

        self.log_step(
            "Verify that the Ad Window is not visible after reloading the page."
        )
        self.logger.debug("Waiting for Ad Window to be displayed.")
        self.page_entry_ad.wait_for_ad_window_visible()
        self.logger.debug(
            "Verifying if Ad Window is not visible after reloading the page."
        )
        self.assert_hidden(
            self.page_entry_ad.get_ad_window(),
            "Ad window should be not visible after reloading the page.",
        )
        self.logger.debug(
            "Verified that Ad window is not visible after reloading the page."
        )
