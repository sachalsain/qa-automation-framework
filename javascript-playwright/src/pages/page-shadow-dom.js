import { BasePage } from "../core/base-page.js";

export class PageShadowDom extends BasePage {
  PAGE_PATH = "/shadowdom";
  PARAGRAPH = "span[slot='my-text']";
  LIST = "ul[slot='my-text'] li";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getParagraphText() { return (await this.getElementText(this.PARAGRAPH)).trim(); }
  async getListText(itemNum) { return (await this.getElementText(`${this.LIST}:nth-child(${itemNum})`)).trim(); }
}
