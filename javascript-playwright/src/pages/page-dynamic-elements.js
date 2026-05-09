import { BasePage } from "../core/base-page.js";

export class PageDynamicElements extends BasePage {
  PAGE_PATH = "/dynamic_loading/";
  BTN_START = "#start button";
  LOADING = "#loading";
  MESSAGE = "#finish h4";

  async open(baseUrl, pageNumber = 1) { await this.navigate(`${baseUrl}${this.PAGE_PATH}${pageNumber}`); }
  getStartBtn() { return this.getElement(this.BTN_START); }
  getMessage() { return this.getElement(this.MESSAGE); }
  async clickStartBtn(state = "hidden") {
    await this.clickElement(this.BTN_START);
    await this.waitForVisibilityChange(this.LOADING, state);
  }
}
