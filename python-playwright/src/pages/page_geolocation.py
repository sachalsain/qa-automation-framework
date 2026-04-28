# Created on : Apr 5, 2026, 1:14:09 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

# from decimal import ROUND_HALF_UP, Decimal

from typing import Literal

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageGeolocation(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/geolocation"
    BTN_LOCATION = "button:text('Where am I?')"
    LATITUDE = "#lat-value"
    LONGITUDE = "#long-value"

    def open(self, base_url: str):
        self.logger.info(f"Opening Geolocation page at {base_url}{self.PAGE_PATH}")
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def click_location_btn(
        self,
        button: Literal["left", "middle", "right"] = "left",
        state: Literal["attached", "detached", "hidden", "visible"] = "visible",
    ):
        self.logger.info("Clicking Location button.")
        self.click_element(self.BTN_LOCATION, button)
        self.logger.debug("Waiting for geolocation to complete.")
        self.wait_for_visibility_change(self.LATITUDE, state)

    def get_geolocation(self) -> tuple:
        self.logger.info("Getting geolocation information from the page.")
        # latitude = Decimal(self.get_text(self.LATITUDE)).quantize(Decimal("0.0"), rounding=ROUND_HALF_UP)
        # longitude = Decimal(self.get_text(self.LONGITUDE)).quantize(Decimal("0.0"), rounding=ROUND_HALF_UP)
        latitude = self.get_element_text(self.LATITUDE)
        longitude = self.get_element_text(self.LONGITUDE)
        self.logger.debug(
            f"Retrieved geolocation: Latitude={latitude}, Longitude={longitude}"
        )
        self.logger.debug("Returning geolocation information as a tuple.")
        geolocation = (latitude, longitude)
        return geolocation
