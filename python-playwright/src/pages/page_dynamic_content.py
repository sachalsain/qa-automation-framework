# Created on : Apr 2, 2026, 11:02:49 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from playwright.sync_api import Locator

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDynamicContent(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/dynamic_content"
    CONTENT = "#content .example"
    IMAGE_CONTAINER = "#content > .row img"
    TEXT_CONTAINER = "#content > .row > .large-10"

    def open(self, base_url: str):
        self.logger.info(f"Opening Dynamic Content page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_content(self) -> Locator:
        self.logger.info("Returning the Content selector.")
        return self.get_element(self.CONTENT)

    def get_all_image_src(self):
        self.logger.info("Getting all image sources on the page")
        images = self.get_elements(self.IMAGE_CONTAINER)
        return [img.get_attribute("src") for img in images]

    def get_all_text(self):
        self.logger.info("Getting all text content on the page")
        text_elements = self.get_elements(self.TEXT_CONTAINER)
        return [text.text_content() for text in text_elements]

    def reload(self):
        self.logger.info("Reloading Dynamic Content page")
        self.reload_page()
