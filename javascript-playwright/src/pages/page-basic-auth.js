import { BasePage } from "../core/base-page.js";

export class PageBasicAuth extends BasePage {
  PAGE_PATH = "/basic_auth";
  HEADING = "#content .example h3";
  PARAGRAPH = "#content .example p";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getHeadingText() { return await this.getElementText(this.HEADING); }
  async getParagraphText() { return await this.getElementText(this.PARAGRAPH); }
}
