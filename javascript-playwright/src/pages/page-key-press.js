import { BasePage } from "../core/base-page.js";

export class PageKeyPress extends BasePage {
  PAGE_PATH = "/key_presses";
  INPUT = "#target";
  RESULT = "#result";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async clickInput() { await this.clickElement(this.INPUT); }
  async getResult() { return (await this.getElementText(this.RESULT)).trim(); }
  async pressKey(key) { await this.pressKeyOnKeyboard(key); }
}
