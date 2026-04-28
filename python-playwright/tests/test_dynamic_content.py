# Created on : Apr 2, 2026, 11:03:01 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_dynamic_content import PageDynamicContent
from src.utilities.logger import get_logger


class TestDynamicContent(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestDynamicContent.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageDynamicContent instance.")
        self.page_dynamic_content = PageDynamicContent(page)

    def test_page_dynamic_content(self):
        self.logger.info("Testing Dynamic Content page.")
        self.logger.debug("Open Dynamic Content page.")
        self.page_dynamic_content.open(self.base_url)

        self.log_step("Verify that content is visible on the page.")
        self.logger.debug("Verifying if content is visible on the page.")
        self.assert_visible(
            self.page_dynamic_content.get_content(),
            "Content should be visible on the page.",
        )
        self.logger.debug("Verified that the Content is visible on the page.")

        self.log_step("Verify that atleast 1 image displayed on the page.")
        self.logger.debug(
            "Requesting 'src' attributes of all images diplayed in the content."
        )
        old_image_sources = self.page_dynamic_content.get_all_image_src()
        self.logger.debug("Verifying if atleast 1 image is displayed on the page.")
        self.assert_greater(
            0,
            len(old_image_sources),
            "There should be at least one image displayed on the page.",
        )
        self.logger.debug("Verified that atleast 1 image is displayed on the page.")

        self.log_step("Verify that atleast 1 text content displayed on the page.")
        self.logger.debug(
            "Requesting all text contents displayed in the content section."
        )
        old_text_contents = self.page_dynamic_content.get_all_text()
        self.logger.debug(
            "Verifying if atleast 1 text content is displayed on the page."
        )
        self.assert_greater(
            0,
            len(old_text_contents),
            "There should be at least one text content displayed on the page.",
        )
        self.logger.debug(
            "Verified that atleast 1 text content is displayed on the page."
        )

        self.log_step("Reload the page and verify content changes")
        self.logger.debug("Reloading the page to refresh content.")
        self.page_dynamic_content.reload()

        self.logger.debug(
            "Requesting 'src' attributes of all images diplayed in the content."
        )
        new_image_sources = self.page_dynamic_content.get_all_image_src()
        self.logger.debug(
            "Requesting all text contents displayed in the content section."
        )
        new_text_contents = self.page_dynamic_content.get_all_text()

        self.log_step("Verify that new content is diplayed on page reload.")
        self.logger.debug("Verifying if new images are now being displayed.")
        self.assert_list_different(
            old_image_sources,
            new_image_sources,
            "Image sources should change after reload.",
        )
        self.logger.debug("Verified that new images are now being displayed.")

        self.logger.debug("Verifying if new text contents are now being displayed.")
        self.assert_list_different(
            old_text_contents,
            new_text_contents,
            "Text content should change after reload.",
        )
        self.logger.debug("Verified that new text contents are now being displayed.")
