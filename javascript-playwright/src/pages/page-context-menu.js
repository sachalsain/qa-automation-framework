import { BasePage } from "../core/base-page.js";

export class PageContextMenu extends BasePage {
  PAGE_PATH = "/context_menu";
  HOT_SPOT = "#hot-spot";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getHotSpot() { return this.getElement(this.HOT_SPOT); }
  async getTextOfAlert() {
    return await this.getOkDialogMessage(() => this.clickElement(this.HOT_SPOT, "right"));
  }
}
