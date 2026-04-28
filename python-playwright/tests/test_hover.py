# Created on : Apr 5, 2026, 4:10:23 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_hover import PageHover
from src.utilities.logger import get_logger


class TestHover(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestHover.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageHover instance.")
        self.page_hover = PageHover(page)

    def test_hover_over_picture(self):
        self.logger.info("Testing Hover page.")
        self.logger.debug("Open Hover page.")
        self.page_hover.open(self.base_url)

        picture_index = 1
        self.log_step("Verify that the correct profile name is displayed.")
        self.logger.debug("Verifying if the correct profile name is displayed.")
        profile_name = self.page_hover.get_displayed_profile_name(picture_index)
        expected_profile_name = "user1"
        self.assert_text_matching(
            expected_profile_name,
            profile_name,
            f"Expected profile name to be '{expected_profile_name}' but got '{profile_name}'",
        )
        self.logger.debug("Verified that the correct profile name is displayed.")
