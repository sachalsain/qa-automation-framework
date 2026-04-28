# Created on : Apr 6, 2026, 8:19:40 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_inputs import PageInputs
from src.utilities.logger import get_logger


class TestInputs(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestInputs.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageInputs instance.")
        self.page_inputs = PageInputs(page)

    def test_inputs(self):
        self.logger.info("Testing Inputs page.")
        self.logger.debug("Open Inputs page.")
        self.page_inputs.open(self.base_url)

        self.logger.debug("Entering value 12345 in the input field.")
        self.page_inputs.fill_number(12345)

        self.log_step("Verify that the value is entered correctly.")
        self.logger.debug("Verifying if the value is entered correctly.")
        self.assert_integer_matching(
            12345, int(self.page_inputs.get_value()), "The value entered must be 12345."
        )
        self.logger.debug("Verified that the value is entered correctly.")
