# Created on : Apr 8, 2026, 8:43:36 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_large_deep_dom import PageLargeDeepDom
from src.utilities.logger import get_logger


class TestLargeDeepDom(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestLargeDeepDom.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageLargeDeepDom instance.")
        self.page_large_deep_dom = PageLargeDeepDom(page)

    def test_large_deep_dom(self):
        self.logger.info("Testing Large Deep Dom page.")
        self.logger.debug("Open Large Deep Dom page.")
        self.page_large_deep_dom.open(self.base_url)

        self.log_step("Verify that the Table is visible.")
        self.logger.debug("Verifying if the Table is visible.")
        self.assert_visible(
            self.page_large_deep_dom.get_table(), "The table should be visible."
        )
        self.logger.debug("Verified that the Table is visible.")

        self.log_step(
            "Verify that the value of the cell on row 10 and column 5 matches."
        )
        self.logger.debug(
            "Verifying if the value of the cell on row 10 and column 5 matches."
        )
        self.logger.debug(
            "Checking if the value of the cell on row 10 and column 5 matches."
        )
        self.assert_text_matching(
            "10.5",
            self.page_large_deep_dom.get_value_of_cell(10, 5),
            "The value of the cell should be '10.5",
        )
        self.logger.debug(
            "Verified that the value of the cell on row 10 and column 5 matches."
        )
