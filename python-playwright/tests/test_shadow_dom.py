# Created on : Apr 8, 2026, 12:08:30 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_shadow_dom import PageShadowDom
from src.utilities.logger import get_logger


class TestShadowDom(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestShadowDom.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageShadowDom instance.")
        self.page_shadow_dom = PageShadowDom(page)

    def test_shadow_dom(self):
        self.logger.info("Testing Shadow Dom page.")
        self.logger.debug("Open Shadow Dom page.")
        self.page_shadow_dom.open(self.base_url)

        self.log_step("Verify that the text in the paragraph matches expected.")
        self.logger.debug("Verifying if the text in the paragraph matches expected.")
        self.assert_text_matching(
            "some different text!",
            self.page_shadow_dom.get_paragraph_text().lower(),
            "The paragraph text must contain 'some different text!'.",
        )
        self.logger.debug("Verified that the text in the paragraph matches expected.")

        self.log_step("Verify that the text in the list matches expected at line 1.")
        self.logger.debug(
            "Verifying if the text in the list matches expected at line 1."
        )
        self.assert_text_matching(
            "some different text!",
            self.page_shadow_dom.get_list_text(1).lower(),
            "The list text must contain 'some different text!' at line 1.",
        )
        self.logger.debug(
            "Verified that the text in the list matches expected at line 1."
        )

        self.log_step("Verify that the text in the list matches expected at line 2.")
        self.logger.debug(
            "Verifying if the text in the list matches expected at line 2."
        )
        self.assert_text_matching(
            "a list!",
            self.page_shadow_dom.get_list_text(2).lower(),
            "The list text must contain 'a list!' at line 2.",
        )
        self.logger.debug(
            "Verified that the text in the list matches expected at line 2."
        )
