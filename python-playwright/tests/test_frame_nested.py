# Created on : Apr 4, 2026, 2:53:32 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_frame_nested import PageFrameNested
from src.utilities.logger import get_logger


class TestFrameNested(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFrameNested.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFrameNested instance.")
        self.page_frame_nested = PageFrameNested(page)

    def test_frame_content(self):
        self.logger.info("Testing Nested Frames page.")
        self.logger.debug("Open Nested Frames page.")
        self.page_frame_nested.open(self.base_url)

        self.log_step("Getting text from frames.")
        self.logger.debug("Getting text from frames.")
        left_content = self.page_frame_nested.get_frame_content("LEFT")
        middle_content = self.page_frame_nested.get_frame_content("MIDDLE")
        right_content = self.page_frame_nested.get_frame_content("RIGHT")
        bottom_content = self.page_frame_nested.get_frame_content("BOTTOM")

        self.log_step("Verify that the text is correct.")
        self.logger.debug("Verifying if the text is correct.")
        assert left_content.upper() == "LEFT", (
            f"Expected 'LEFT' but got '{left_content}'"
        )
        assert middle_content.upper() == "MIDDLE", (
            f"Expected 'MIDDLE' but got '{middle_content}'"
        )
        assert right_content.upper() == "RIGHT", (
            f"Expected 'RIGHT' but got '{right_content}'"
        )
        assert bottom_content.upper() == "BOTTOM", (
            f"Expected 'BOTTOM' but got '{bottom_content}'"
        )
        self.logger.debug("Verified that the text from all frames is correct.")
