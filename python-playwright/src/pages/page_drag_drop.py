# Created on : Apr 2, 2026, 10:10:44 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageDragDrop(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/drag_and_drop"
    BOX_A = "#column-a"
    BOX_B = "#column-b"

    def open(self, base_url: str):
        self.logger.info(f"Opening Drag and Drop page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def perform_drag_drop(self):
        self.logger.info("Performing drag and drop action.")
        self.drag_drop(self.BOX_A, self.BOX_B)

    def get_text_of_box_a(self) -> str | None:
        self.logger.info("Getting text from Box A")
        return self.get_element_text(self.BOX_A)

    def get_text_of_box_b(self) -> str | None:
        self.logger.info("Getting text from Box B")
        return self.get_element_text(self.BOX_B)
