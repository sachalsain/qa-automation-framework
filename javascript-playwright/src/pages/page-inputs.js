import { BasePage } from "../core/base-page.js";

export class PageInputs extends BasePage {
  PAGE_PATH = "/inputs";
  INPUT = "input[type='number']";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async fillNumber(number) { await this.fillValueInput(this.INPUT, number); }
  async getValue() { return await this.getValueInput(this.INPUT); }
}
