import { BasePage } from "../core/base-page.js";

export class PageHorizontalSlider extends BasePage {
  PAGE_PATH = "/horizontal_slider";
  SLIDER = "#content input[type='range']";
  SLIDER_VALUE = "#range";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async setSliderValue(finalValue) {
    await this.clickElement(this.SLIDER);
    let currentValue = await this.getSliderValue();
    while (currentValue < finalValue) {
      await this.pressKeyOnKeyboard("ArrowRight");
      currentValue = await this.getSliderValue();
    }
    while (currentValue > finalValue) {
      await this.pressKeyOnKeyboard("ArrowLeft");
      currentValue = await this.getSliderValue();
    }
  }
  async getSliderValue() { return Number((await this.getElementText(this.SLIDER_VALUE)).trim()); }
}
