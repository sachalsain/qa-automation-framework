import { BasePage } from "../core/base-page.js";

export class PageDisappearingElements extends BasePage {
  PAGE_PATH = "/disappearing_elements";
  MENU = "#content";
  MENU_LIST = "#content ul li";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getMenu() { return this.getElement(this.MENU); }
  async getListCount() { return await this.getElementCount(this.MENU_LIST); }
}
