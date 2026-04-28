# Created on : Apr 2, 2026, 9:13:54 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_disappearing_elements import PageDisappearingElements
from src.utilities.logger import get_logger


class TestDisappearingElements(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDisappearingElements.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDisappearingElements instance.")
        self.page_disappearing_elements = PageDisappearingElements(page)

    def test_page_disappearing_elements(self):
        self.logger.info("Testing Disappearing Elements page.")
        self.logger.debug("Open Disappearing Elements page.")
        self.page_disappearing_elements.open(self.base_url)

        self.log_step("Verify that the menu is visible on the page.")
        self.logger.debug("Verifying if menu is visible on the page.")
        self.assert_visible(
            self.page_disappearing_elements.get_menu(),
            "Menu should be visible on the page.",
        )
        self.logger.debug("Verified that the menu is visible on the page.")

        self.log_step("Verify that there are 4 or 5 items in the menu.")
        self.logger.debug("Verifying if there are 4 or 5 items in the menu.")
        actual_value = self.page_disappearing_elements.get_list_count()
        self.assert_approx_size_of_list(
            [4, 5],
            actual_value,
            f"Menu should have 4 or 5 items but found {actual_value}",
        )
        self.logger.debug("Verified that there are 4 or 5 items in the menu.")
