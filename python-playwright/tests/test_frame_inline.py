# Created on : Apr 4, 2026, 4:05:40 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_frame_inline import PageFrameInline
from src.utilities.logger import get_logger


class TestFrameInline(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFrameInline.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFrameInline instance.")
        self.page_frame_inline = PageFrameInline(page)

    def test_frame_content(self):
        self.logger.info("Testing Inline Frames page.")
        self.logger.debug("Open Inline Frames page.")
        self.page_frame_inline.open(self.base_url)

        self.log_step("Getting text from frames.")
        self.logger.debug("Getting text from frames.")
        left_content = self.page_frame_inline.get_frame_content()

        self.log_step("Verify that the text is correct.")
        self.logger.debug("Verifying if the text is correct.")
        self.assert_text_matching(
            "content goes here",
            left_content.lower(),
            f"Expected 'content goes here' but got '{left_content}'",
        )
        self.logger.debug("Verified that the text from inline frame is correct.")
