# Created on : Apr 4, 2026, 2:53:18 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageFrameNested(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/nested_frames"
    TOP_FRAME = "frame[name='frame-top']"
    TOP_LEFT_FRAME = "frame[name='frame-left']"
    TOP_MIDDLE_FRAME = "frame[name='frame-middle']"
    TOP_RIGHT_FRAME = "frame[name='frame-right']"
    BOTTOM_FRAME = "frame[name='frame-bottom']"

    def open(self, base_url: str):
        self.logger.info(f"Opening Nested Frames page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def get_frame_content(self, frame: str) -> str:
        self.logger.info(f"getting the content of the {frame} frame.")
        content = ""
        match frame:
            case "LEFT":
                content = self.get_nested_frame_text(
                    self.TOP_FRAME, self.TOP_LEFT_FRAME
                )
                # content = self.page.frame_locator(self.TOP_FRAME).frame_locator(self.TOP_LEFT_FRAME).locator("body").text_content().strip()
            case "MIDDLE":
                content = self.get_nested_frame_text(
                    self.TOP_FRAME, self.TOP_MIDDLE_FRAME
                )
            #     content = self.page.frame_locator(self.TOP_FRAME).frame_locator(self.TOP_MIDDLE_FRAME).locator("body").text_content().strip()
            case "RIGHT":
                content = self.get_nested_frame_text(
                    self.TOP_FRAME, self.TOP_RIGHT_FRAME
                )
            #     content = self.page.frame_locator(self.TOP_FRAME).frame_locator(self.TOP_RIGHT_FRAME).locator("body").text_content().strip()
            case "BOTTOM":
                content = self.get_frame_text(self.BOTTOM_FRAME)
            #     content = self.page.frame_locator(self.BOTTOM_FRAME).locator("body").text_content().strip()
            case _:
                self.logger.error(f"Invalid frame name: {frame}")
                raise ValueError(f"Invalid frame name: {frame}")
        self.logger.debug(
            f"Returning the content of the {frame} frame which is: {content}"
        )
        return content
