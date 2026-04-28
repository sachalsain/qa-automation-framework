# Created on : Apr 5, 2026, 4:10:08 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageHover(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/hovers"
    PICTURE = ".figure"
    PROFILE = ".figcaption h5"

    def open(self, base_url: str):
        self.logger.info(f"Opening Hover page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def hover_over_picture(self, picture_index: int):
        self.logger.info(f"Hovering over picture at index {picture_index}")
        picture_selector = f"{self.PICTURE}:nth-of-type({picture_index})"
        self.hover_on(picture_selector)

    def get_displayed_profile_name(self, picture_index: int) -> str:
        self.hover_over_picture(picture_index)
        self.logger.info(
            f"Getting displayed profile name for picture at index {picture_index}"
        )
        profile_selector = f"{self.PICTURE}:nth-of-type({picture_index}) {self.PROFILE}"
        self.logger.debug(f"Selector: {profile_selector}")
        text = self.get_element_text(profile_selector)
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return text.split(":", 1)[1].strip()
