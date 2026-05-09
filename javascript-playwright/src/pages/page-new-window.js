import { BasePage } from "../core/base-page.js";

export class PageNewWindow extends BasePage {
  PAGE_PATH = "/windows";
  NEW_WINDOW_LINK = ".example a";
  NEW_WINDOW_TEXT = "h3";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async clickToCreateNewPage() { return await this.createNewWindow(this.NEW_WINDOW_LINK); }
  async getNewPageText(window) { return (await this.getWindowElementText(window, this.NEW_WINDOW_TEXT)).trim(); }
}
