import { BasePage } from "../core/base-page.js";

export class PageInfiniteScroll extends BasePage {
  PAGE_PATH = "/infinite_scroll";
  PARAGRAPH = ".jscroll-added";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getParaCount() { return await this.getElement(this.PARAGRAPH).count(); }
  async scrollDown() {
    const currentCount = await this.getParaCount();
    await this.page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
    await this.page.waitForFunction((count) => document.querySelectorAll(".jscroll-added").length > count, currentCount);
  }
}
