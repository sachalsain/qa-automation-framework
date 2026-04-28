# Created on : Apr 2, 2026, 11:03:01 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_dynamic_elements import PageDynamicElements
from src.utilities.logger import get_logger


class TestDynamicElements(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDynamicElements.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDynamicElements instance.")
        self.page_dynamic_elements = PageDynamicElements(page)

    def test_page_dynamic_elements_hidden(self):
        self.logger.info("Testing Hidden Dynamic Elements page.")
        self.logger.debug("Open Dynamic Elements page.")
        self.page_dynamic_elements.open(self.base_url, 1)

        self.log_step("Verify that Start button is visible on the page.")
        self.logger.debug("Verifying if Start button is visible on the page.")
        self.assert_visible(
            self.page_dynamic_elements.get_start_btn(),
            "Start button should be visible on the page.",
        )
        self.logger.debug("Verified that Start button is visible on the page.")

        self.log_step("Verify that message is hidden by default on the page.")
        self.logger.debug("Verifying if message is hidden by default on the page.")
        self.assert_hidden(
            self.page_dynamic_elements.get_message(),
            "Message should be hidden by default on the page.",
        )
        self.logger.debug("Verified that message is hidden by default on the page.")

        self.log_step("Click the Start button to load the hidden element.")
        self.page_dynamic_elements.click_start_btn("hidden")

        self.log_step("Verify that Message is visible after loading completes.")
        self.logger.debug("Verifying if Message is visible after loading completes.")
        self.assert_visible(
            self.page_dynamic_elements.get_message(),
            "Message should be visible after loading completes.",
        )
        self.logger.debug("Verified that Message is visible after loading completes.")

        self.log_step("Verify that Start button is hidden after loading completes.")
        self.logger.debug(
            "Verifying if Start button is hidden after loading completes."
        )
        self.assert_hidden(
            self.page_dynamic_elements.get_start_btn(),
            "Start button should be hidden after loading completes.",
        )
        self.logger.debug(
            "Verified that Start button is hidden after loading completes."
        )

    def test_page_dynamic_elements_insert(self):
        self.logger.info("Testing Inserted Dynamic Elements page.")
        self.logger.debug("Open Dynamic Elements page.")
        self.page_dynamic_elements.open(self.base_url, 2)

        self.log_step("Verify that Start button is visible on the page.")
        self.logger.debug("Verifying if Start button is visible on the page.")
        self.assert_visible(
            self.page_dynamic_elements.get_start_btn(),
            "Start button should be visible on the page.",
        )
        self.logger.debug("Verified that Start button is visible on the page.")

        self.log_step("Verify that message is hidden by default on the page.")
        self.logger.debug("Verifying if message is hidden by default on the page.")
        self.assert_hidden(
            self.page_dynamic_elements.get_message(),
            "Message should be hidden by default on the page.",
        )
        self.logger.debug("Verified that message is hidden by default on the page.")

        self.log_step("Click the Start button to load the hidden element.")
        self.page_dynamic_elements.click_start_btn("hidden")

        self.log_step("Verify that Message is visible after loading completes.")
        self.logger.debug("Verifying if Message is visible after loading completes.")
        self.assert_visible(
            self.page_dynamic_elements.get_message(),
            "Message should be visible after loading completes.",
        )
        self.logger.debug("Verified that Message is visible after loading completes.")

        self.log_step("Verify that Start button is hidden after loading completes.")
        self.logger.debug(
            "Verifying if Start button is hidden after loading completes."
        )
        self.assert_hidden(
            self.page_dynamic_elements.get_start_btn(),
            "Start button should be hidden after loading completes.",
        )
        self.logger.debug(
            "Verified that Start button is hidden after loading completes."
        )
