# Created on : Apr 8, 2026, 5:12:40 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_shifting_content_menu import PageShiftingContentMenu
from src.utilities.logger import get_logger


class TestShiftingContentMenu(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestShiftingContentMenu.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageShiftingContentMenu instance.")
        self.page_shifting_content_menu = PageShiftingContentMenu(page)

    def test_shifting_content_menu(self):
        self.logger.info("Testing Shifting Content Menu page.")
        self.logger.debug("Open Shifting Content Menu page.")
        self.page_shifting_content_menu.open(self.base_url)

        self.logger.debug("Clicking the Mode link.")
        url = self.page_shifting_content_menu.click_link(1)

        self.log_step("Verify that the link contains mode in it.")
        self.logger.debug("Verifying if the link contains mode in it.")
        self.assert_text_matching("mode", url, "The URL text must contain 'mode'.")
        self.logger.debug("Verified the link contains mode in it.")

        self.logger.debug("Clicking the Pixel Shift link.")
        url = self.page_shifting_content_menu.click_link(2)

        self.log_step("Verify that the link contains pixel_shift in it.")
        self.logger.debug("Verifying if the link contains pixel_shift in it.")
        self.assert_text_matching(
            "pixel_shift", url, "The URL text must contain 'pixel_shift'."
        )
        self.logger.debug("Verified the link contains pixel_shift in it.")

        self.logger.debug("Clicking the Mode and Pixel Shift link.")
        url = self.page_shifting_content_menu.click_link(3)

        self.log_step("Verify that the link contains mode and pixel_shift in it.")
        self.logger.debug("Verifying if the link contains mode and pixel_shift in it.")
        self.assert_text_matching(
            "pixel_shift", url, "The URL text must contain 'mode and pixel_shift'."
        )
        self.logger.debug("Verified the link contains mode and pixel_shift in it.")
