import { BasePage } from "../core/base-page.js";

export class PageLargeDeepDom extends BasePage {
  PAGE_PATH = "/large";
  TABLE = "#large-table";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getTable() { return this.getElement(this.TABLE); }
  async getValueOfCell(row, cell) { return (await this.getElementText(`.row-${row} .column-${cell}`)).trim(); }
}
