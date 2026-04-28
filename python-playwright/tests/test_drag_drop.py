# Created on : Apr 2, 2026, 10:10:52 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_drag_drop import PageDragDrop
from src.utilities.logger import get_logger


class TestDragDrop(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDragDrop.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDragDrop instance.")
        self.page_drag_drop = PageDragDrop(page)

    def test_page_drag_drop(self):
        self.logger.info("Testing Drag and Drop page.")
        self.logger.debug("Open Drag and Drop page.")
        self.page_drag_drop.open(self.base_url)

        self.log_step("Verify that box 1 displays A.")
        self.logger.debug("Verifying if box 1 displays A.")
        self.assert_text_matching(
            "A", self.page_drag_drop.get_text_of_box_a(), "Box 1 should display A."
        )
        self.logger.debug("Verified that box 1 displays A.")

        self.log_step("Verify that box 2 displays B.")
        self.logger.debug("Verifying if box 2 displays B.")
        self.assert_text_matching(
            "B", self.page_drag_drop.get_text_of_box_b(), "Box 2 should display B."
        )
        self.logger.debug("Verified that box 2 displays B.")

        self.log_step("Perform drag and drop action to swap the boxes")
        self.page_drag_drop.perform_drag_drop()

        self.log_step("Again Verify that box 1 displays B")
        self.logger.debug("Verifying again if box 1 displays B.")
        self.assert_text_matching(
            "B", self.page_drag_drop.get_text_of_box_a(), "Box 1 should display B."
        )
        self.logger.debug("Verified that box 1 displays B.")

        self.log_step("Again Verify that box 2 displays A")
        self.logger.debug("Verifying again if box 2 displays A.")
        self.assert_text_matching(
            "A", self.page_drag_drop.get_text_of_box_b(), "Box 2 should display A."
        )
        self.logger.debug("Verified that box 2 displays A.")
