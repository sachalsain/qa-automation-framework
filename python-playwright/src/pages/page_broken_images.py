# Created on : Mar 31, 2026, 11:21:23 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator, Response

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageBrokenImages(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/broken_images"
    IMAGE_CONTAINER = ".example"
    IMAGES_LIST = ".example img"

    def open(self, base_url: str):
        self.logger.info(f"Opening Broken Images page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_image_container(self) -> Locator:
        self.logger.info("Returning the Image Container Locator.")
        return self.get_element(self.IMAGE_CONTAINER)

    def getAllImages(self) -> list[Locator]:
        self.logger.info("Returning all image locators on the page.")
        return self.get_elements(self.IMAGES_LIST)

    def run_js_to_check_img_complete(self, img: Locator, script: str) -> bool:
        self.logger.info(
            "Running Script to check if image is complete and returning result."
        )
        return self.execute_script(img, script)

    def get_status_code(self, base_url, imageSrc) -> Response:
        self.logger.info(f"Getting API response from '{base_url + '/' + imageSrc}'")
        return self.get_API_response(base_url + "/" + imageSrc)
