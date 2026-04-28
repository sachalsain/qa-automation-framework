# Created on : Apr 6, 2026, 8:04:50 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_infinite_scroll import PageInfiniteScroll
from src.utilities.logger import get_logger


class TestInfiniteScroll(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestInfiniteScroll.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageInfiniteScroll instance.")
        self.page_infinite_scroll = PageInfiniteScroll(page)

    def test_infinite_scroll(self):
        self.logger.info("Testing Infinite Scroll page.")
        self.logger.debug("Open Infinite Scroll page.")
        self.page_infinite_scroll.open(self.base_url)

        self.log_step("Verify that the count of the paragraphs is 1 before scroll.")
        self.logger.debug(
            "Verifying if the count of the paragraphs is 1 before scroll."
        )
        self.assert_integer_matching(
            1,
            self.page_infinite_scroll.get_para_count(),
            "Expected initial paragraph count to be 1.",
        )
        self.logger.debug(
            "Verified that the count of the paragraphs is 1 before scroll."
        )

        self.logger.debug("Scrolling down the page.")
        self.page_infinite_scroll.scroll_down()

        self.log_step(
            "Verify that the count of the paragraphs is greater than 1 after scrolling."
        )
        self.logger.debug(
            "Verifying if the count of the paragraphs is greater than 1 after scrolling."
        )
        self.assert_greater(
            1,
            self.page_infinite_scroll.get_para_count(),
            "Expected paragraph count to be greater than 1 after scrolling.",
        )
        self.logger.debug(
            "Verified that the count of the paragraphs is greater than 1 after scrolling."
        )
