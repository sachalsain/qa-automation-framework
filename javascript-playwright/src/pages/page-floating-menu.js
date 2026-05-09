import { BasePage } from "../core/base-page.js";

export class PageFloatingMenu extends BasePage {
  PAGE_PATH = "/floating_menu";
  MENU = "#menu";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getMenu() { return this.getElement(this.MENU); }
  async scrollDown() { await this.page.evaluate(() => window.scrollTo(0, document.body.scrollHeight)); }
}
