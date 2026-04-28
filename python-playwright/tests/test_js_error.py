# Created on : Apr 8, 2026, 8:08:12 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_js_error import PageJSError
from src.utilities.logger import get_logger


class TestJSError(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestJSError.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageJSError instance.")
        self.page_js_error = PageJSError(page)

    def test_page_js_error(self):
        self.logger.info("Testing JS Error page.")
        self.logger.debug("Open JS Error page.")
        self.page_js_error.open(self.base_url)

        self.log_step("Verify that errors list is not empty.")
        self.logger.debug("Verifying if errors list is not empty.")
        self.logger.debug(self.page_js_error.getErrors())
        self.assert_greater(
            0,
            len(self.page_js_error.getErrors()),
            "There should be at least one JS error on the page.",
        )
        self.logger.debug("Verified that errors list is not empty.")

        self.log_step(
            "Verify that if the error message contains 'undefined (reading 'xyz')'."
        )
        self.logger.debug(
            "Verifying if the error message contains 'undefined (reading 'xyz')'."
        )
        for error in self.page_js_error.getErrors():
            self.assert_text_matching(
                "undefined (reading 'xyz')",
                str(error),
                f"Expected 'undefined (reading 'xyz')' error but found '{str(error)}' error.",
            )
        self.logger.debug(
            "Verified that the error message contains 'undefined (reading 'xyz')'."
        )
