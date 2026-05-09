import { BasePage } from "../core/base-page.js";

export class PageDropdown extends BasePage {
  PAGE_PATH = "/dropdown";
  DROPDOWN = "#dropdown";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async selectValue(value) { await this.selectDropdown(this.DROPDOWN, value); }
  async getSelectedValue() { return (await this.getTextSelectedDropdown(this.DROPDOWN)).trim(); }
}
