# Created on : Mar 31, 2026, 1:08:04 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_challenging_dom import PageChallengingDOM
from src.utilities.logger import get_logger


class TestChallengingDOM(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestChallengingDOM.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageChallengingDOM instance.")
        self.page_challenging_dom = PageChallengingDOM(page)

    def test_challenging_dom_table(self):
        self.logger.info("Testing Challenging DOM page.")
        self.logger.debug("Open Challenging DOM page.")
        self.page_challenging_dom.open(self.base_url)

        self.log_step("Verify the number of headers in the table to be 7.")
        self.logger.debug("Verifying if the number of headers in the table is 7.")
        actual_value = self.page_challenging_dom.get_table_headers_count()
        self.assert_integer_matching(
            7,
            actual_value,
            f"Table should have 7 headers but got {actual_value} headers.",
        )
        self.logger.debug("Verified the number of headers in the table to be 7.")

        self.log_step("Verify the number of data rows in the table to be 10.")
        self.logger.debug("Verifying if the number of data rows in the table are 10.")
        actual_value = self.page_challenging_dom.get_table_data_row_count()
        self.assert_integer_matching(
            10,
            actual_value,
            f"Table should have 10 data rows but got {actual_value} rows.",
        )
        self.logger.debug("Verified the number of data rows in the table to be 10.")

        self.log_step("Verify the value of a specific cell in the table.")
        self.logger.debug(
            "Verifying if the value of a specific cell in the table is as expected."
        )
        actual_value = self.page_challenging_dom.get_table_data_value(2, 3)
        self.assert_text_matching(
            "Adipisci1",
            actual_value,
            f"Expected cell value to be 'Adipisci1' but got '{actual_value}'",
        )
        self.logger.debug("Verified the value of a specific cell in the table.")
