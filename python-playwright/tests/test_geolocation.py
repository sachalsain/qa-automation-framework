# Created on : Apr 5, 2026, 1:14:21 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

# from decimal import ROUND_HALF_UP, Decimal

import pytest

from src.core.base_test import BaseTest
from src.pages.page_geolocation import PageGeolocation
from src.utilities.logger import get_logger


class TestGeolocation(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestGeolocation.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageGeolocation instance.")
        self.page_geolocation = PageGeolocation(page)

    def test_geolocation(self):
        self.logger.info("Testing geolocation functionality.")
        self.logger.debug("Open geolocation page.")
        self.page_geolocation.open(self.base_url)

        self.log_step("Getting geolocation information.")
        self.logger.debug("Clicking 'Where am I?' button to retrieve geolocation.")
        self.page_geolocation.click_location_btn()

        self.logger.debug("Retrieving geolocation information from the page.")
        geolocation = self.page_geolocation.get_geolocation()
        self.logger.debug(
            f"Retrieved geolocation: Latitude={geolocation[0]}, Longitude={geolocation[1]}"
        )

        self.log_step("Verify that the geolocation information is correct.")
        self.logger.debug("Verifying if the geolocation information is correct.")
        # expected_latitude = Decimal(self.config.get("geolocation_latitude", "")).quantize(Decimal("0.0"), rounding=ROUND_HALF_UP)
        # expected_longitude = Decimal(self.config.get("geolocation_longitude", "")).quantize(Decimal("0.0"), rounding=ROUND_HALF_UP)
        expected_latitude = self.config.get("geolocation_latitude", "")
        expected_longitude = self.config.get("geolocation_longitude", "")
        self.assert_float_matching(
            float(geolocation[0]),
            float(expected_latitude),
            f"Expected latitude '{expected_latitude}' but got '{geolocation[0]}'",
        )
        self.assert_float_matching(
            float(geolocation[1]),
            float(expected_longitude),
            f"Expected longitude '{expected_longitude}' but got '{geolocation[1]}'",
        )
        self.logger.debug("Verified that the geolocation information is correct.")
