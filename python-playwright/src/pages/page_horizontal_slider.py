# Created on : Apr 5, 2026, 2:04:36 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from src.core.base_page import BasePage
from src.utilities.logger import get_logger


class PageHorizontalSlider(BasePage):
    logger = get_logger(__name__)

    PAGE_PATH = "/horizontal_slider"
    SLIDER = "#content input[type='range']"
    SLIDER_VALUE = "#range"

    def open(self, base_url: str):
        self.logger.info(
            f"Opening Horizontal Slider page at {base_url}{self.PAGE_PATH}"
        )
        self.navigate(f"{base_url}{self.PAGE_PATH}")

    def set_slider_value(self, final_value: float):
        self.logger.info(f"Setting slider value to {final_value}")
        self.logger.debug("Clikcking the Slider for focusing it.")
        self.click_element(self.SLIDER)
        current_value = self.get_slider_value()

        while current_value < final_value:
            self.logger.debug(
                f"Pressing the right arrow key until the slider value reaches {final_value}."
            )
            self.press_key_on_keyboard("ArrowRight")
            current_value = self.get_slider_value()

        while current_value > final_value:
            self.logger.debug(
                f"Pressing the left arrow key until the slider value reaches {final_value}."
            )
            self.press_key_on_keyboard("ArrowLeft")
            current_value = self.get_slider_value()

    def get_slider_value(self) -> float:
        self.logger.info("Retrieving slider value")
        text = self.get_element_text(self.SLIDER_VALUE)
        if text is None:
            self.logger.error("'None' data type recieved from Message.")
            raise ValueError("'None' data type recieved from Message.")
        return float(text.strip())
