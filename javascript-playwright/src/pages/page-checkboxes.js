import { BasePage } from "../core/base-page.js";

export class PageCheckboxes extends BasePage {
  PAGE_PATH = "/checkboxes";
  CHECKBOXES = "form#checkboxes input[type='checkbox']";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getCheckboxesCount() { return await this.getElementCount(this.CHECKBOXES); }
  getCheckbox(index) { return this.getElement(`${this.CHECKBOXES}:nth-of-type(${index})`); }
  async toggleCheckbox(index) { await this.clickElement(`${this.CHECKBOXES}:nth-of-type(${index})`); }
}
