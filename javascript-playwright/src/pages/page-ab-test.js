import { BasePage } from "../core/base-page.js";

export class PageABTesting extends BasePage {
  PAGE_PATH = "/abtest";
  HEADER = "h3";
  DESCRIPTION = "p";

  async open(baseUrl) {
    await this.navigate(`${baseUrl}${this.PAGE_PATH}`);
  }

  async getHeaderText() {
    return await this.getElementText(this.HEADER);
  }

  async getDescriptionText() {
    return await this.getElementText(this.DESCRIPTION);
  }
}
