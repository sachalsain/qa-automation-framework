# Created on : Apr 6, 2026, 8:04:35 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageInfiniteScroll(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/infinite_scroll"
    PARAGRAPH = ".jscroll-added"

    def open(self, base_url: str):
        self.logger.info(f"Opening Infinite Scroll page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_para_count(self) -> int:
        self.logger.info("Getting the count of loaded paragraphs.")
        return self.get_element(self.PARAGRAPH).count()

    def scroll_down(self):
        self.logger.info("Scrolling down the page to load more content.")
        self.execute_script(
            self.get_element(self.PARAGRAPH), "element => element.scrollIntoView()"
        )
        self.logger.debug("Waiting for the new content to load.")
        self.wait_for_function(
            "() => document.querySelectorAll('.jscroll-added').length > "
            + str(self.get_para_count())
        )
