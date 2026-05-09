import { BasePage } from "../core/base-page.js";

export class PageChallengingDOM extends BasePage {
  PAGE_PATH = "/challenging_dom";
  TABLE = "table";
  TABLE_HEADERS = `${this.TABLE} thead tr th`;
  TABLE_DATA_ROWS = `${this.TABLE} tbody tr`;

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getTableHeadersCount() { return await this.getElementCount(this.TABLE_HEADERS); }
  async getTableDataRowCount() { return await this.getElementCount(this.TABLE_DATA_ROWS); }
  async getTableDataValue(row, column) {
    return await this.getElementText(`${this.TABLE_DATA_ROWS}:nth-child(${row}) td:nth-child(${column})`);
  }
}
