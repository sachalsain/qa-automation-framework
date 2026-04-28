# Created on : Mar 31, 2026, 11:21:41 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_broken_images import PageBrokenImages
from src.utilities.logger import get_logger


class TestBrokenImages(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestBrokenImages.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageBrokenImages instance.")
        self.page_broken_images = PageBrokenImages(page)

    def test_broken_images(self):
        self.logger.info("Testing Broken Images page.")
        self.logger.debug("Opening Broken Images page.")
        self.page_broken_images.open(self.base_url)

        self.log_step("Verify Images container is visible.")
        self.logger.debug("Verifying if Images container is visible.")
        self.assert_visible(
            self.page_broken_images.get_image_container(),
            "Images container should be visible.",
        )
        self.logger.debug("Verified that Images container is visible.")

        self.log_step("Verify all images on the page are visible.")
        self.logger.debug("Requesting all images on the page.")
        images = self.page_broken_images.getAllImages()
        failed_images = []
        for img in images:
            self.logger.debug(f"Checking image with src: {img.get_attribute('src')}")
            if not self.page_broken_images.run_js_to_check_img_complete(
                img,
                "img => img.complete && typeof img.naturalWidth === 'number' && img.naturalWidth > 0",
            ):
                self.logger.debug(
                    f"image with src: {img.get_attribute('src')} not loaded. Adding to failed list."
                )
                failed_images.append(img.get_attribute("src"))
        self.logger.debug("Verifying if all images on the page are visible.")
        self.assert_size_of_list(
            len(failed_images),
            0,
            f"Images with src '{failed_images}' should be loaded.",
        )
        self.logger.debug("Verified that all images on the page are visible.")

    def test_broken_images_API(self):
        self.logger.info("Testing Broken Images API.")
        self.logger.debug("Open Broken Images page.")
        self.page_broken_images.open(self.base_url)

        self.log_step("Verify all images on the page gives SUCCESS using API.")
        self.logger.debug("Requesting all images on the page.")
        images = self.page_broken_images.getAllImages()
        failed_images = []
        for img in images:
            src = img.get_attribute("src")
            self.logger.debug(f"Checking image with src: {src}")
            response = self.page_broken_images.get_status_code(self.base_url, src)

            self.logger.debug(
                f"Checking Response received for: {img.get_attribute('src')}"
            )
            if response.status != 200:
                self.logger.debug(
                    f"Response received for {src} not '200'. But got {response.status}. Adding to failed list."
                )
                failed_images.append(src)
        self.logger.debug("Verifying if all images on the page get 200 status code.")
        self.assert_size_of_list(
            len(failed_images),
            0,
            f"Images with src '{failed_images}' should return 200 status code.",
        )
        self.log_step("Verified that all images on the page gives SUCCESS using API.")
