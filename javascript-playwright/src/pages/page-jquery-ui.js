import { BasePage } from "../core/base-page.js";

export class PageJqueryUi extends BasePage {
  PAGE_PATH = "/jqueryui/menu";
  MENU_DISABLED = "text=Disabled";
  MENU_ENABLED = "text=Enabled";
  MENU_DOWNLOADS = "text=Downloads";
  MENU_CSV = "text=CSV";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getMenuDisabled() { return this.getElement(this.MENU_DISABLED); }
  getMenuEnabled() { return this.getElement(this.MENU_ENABLED); }
  async hoverOverEnabled() {
    await this.hoverOn(this.MENU_ENABLED);
    await this.waitForVisibilityChange(this.MENU_DOWNLOADS, "visible");
  }
  getMenuDownloads() { return this.getElement(this.MENU_DOWNLOADS); }
  async hoverOverDownloads() {
    await this.hoverOn(this.MENU_DOWNLOADS);
    await this.waitForVisibilityChange(this.MENU_CSV, "visible");
  }
  getMenuCsv() { return this.getElement(this.MENU_CSV); }
}
