# Created on : Apr 4, 2026, 4:05:21 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageFrameInline(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/iframe"
    INLINE_FRAME = "#mce_0_ifr"
    PARAGRAPH = "body p"

    def open(self, base_url: str):
        self.logger.info(f"Opening Inline Frames page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_frame_content(self) -> str:
        self.logger.info("Getting the content of the frame.")
        return (
            self.page.frame_locator(self.INLINE_FRAME)
            .locator(self.PARAGRAPH)
            .text_content()
            .strip()
        )
