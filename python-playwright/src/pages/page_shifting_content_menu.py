# Created on : Apr 8, 2026, 5:12:24 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageShiftingContentMenu(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/shifting_content/menu"
    LINK1 = ".example  p:nth-child(3) a"
    LINK2 = ".example  p:nth-child(4) a"
    LINK3 = ".example  p:nth-child(5) a"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Shifting Content Menu page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def click_link(self, val: int) -> str:
        self.logger.info("Clicking the required link")
        match val:
            case 1:
                self.click_element(self.LINK1)
            case 2:
                self.click_element(self.LINK2)
            case 3:
                self.click_element(self.LINK3)
            case _:
                raise Exception("Link not found")
        return self.get_url()
