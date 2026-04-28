# Created on : Apr 5, 2026, 2:04:48 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_horizontal_slider import PageHorizontalSlider
from src.utilities.logger import get_logger


class TestHorizontalSlider(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestHorizontalSlider.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageHorizontalSlider instance.")
        self.page_horizontal_slider = PageHorizontalSlider(page)

    def test_horizontal_slider(self):
        self.logger.info("Testing Horizontal Slider page.")
        self.logger.debug("Open Horizontal Slider page.")
        self.page_horizontal_slider.open(self.base_url)

        self.log_step("Setting the slider value to 4.5.")
        self.logger.debug("Setting the slider value to 4.5.")
        self.page_horizontal_slider.set_slider_value(4.5)

        self.log_step("Verify that the slider value is correct.")
        self.logger.debug("Verify7ing if the slider value is correct.")
        slider_value = self.page_horizontal_slider.get_slider_value()
        self.assert_float_matching(
            4.5, slider_value, f"Expected slider value to be 4.5 but got {slider_value}"
        )
        self.logger.debug("Verified that the slider value is correct.")
