# Created on : Apr 8, 2026, 9:08:31 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_new_window import PageNewWindow
from src.utilities.logger import get_logger


class TestNewWindow(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestNewWindow.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageNewWindow instance.")
        self.page_new_window = PageNewWindow(page)

    def test_new_window(self):
        self.logger.info("Testing New Window page.")
        self.logger.debug("Open New Window page.")
        self.page_new_window.open(self.base_url)

        self.logger.debug("Creating new Window by clicking the link.")
        new_window = self.page_new_window.click_to_create_new_page()

        self.log_step("Verify that the heading on new page matches.")
        self.logger.debug("Verifying if the heading on new page matches.")
        self.assert_text_matching(
            "new window",
            self.page_new_window.get_new_page_text(new_window).lower(),
            "The heading on new page should display 'New Window'.",
        )
        self.logger.debug(
            "Verified that the value of the cell on row 10 and column 5 matches."
        )
