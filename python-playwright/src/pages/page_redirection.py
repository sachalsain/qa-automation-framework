# Created on : Apr 8, 2026, 10:02:39 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}


from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageRedirection(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/redirector"
    LINK = "#redirect"
    EXPECTED_URL = "https://the-internet.herokuapp.com/status_codes"
    HEADING_NEW_PAGE = "#content .example h3"

    def open(self, base_url: str):
        self.logger.info(f"Opening Broken Images page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def click_link(self):
        self.logger.info("Clicking link")
        self.click_element(self.LINK)

    def get_expected_url(self) -> str:
        self.logger.info("Getting Heading of redirected page")
        return self.EXPECTED_URL

    def get_redirected_url(self) -> str:
        self.logger.info("Getting Page URL")
        return self.get_url()

    def get_redirected_heading(self) -> str:
        self.logger.info("Getting Heading of redirected page")
        text = self.get_element_text(self.HEADING_NEW_PAGE)
        if text is None:
            self.logger.error("'None' data type recieved from Heading of new page.")
            raise ValueError("'None' data type recieved from Heading of new page.")
        return text.strip()

    def get_status_code(self, url: str) -> int:
        self.logger.info(f"Getting API response for: '{self.EXPECTED_URL}'")
        return self.get_API_response(self.EXPECTED_URL + url).status
