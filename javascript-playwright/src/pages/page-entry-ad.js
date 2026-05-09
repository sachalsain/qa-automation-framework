import { BasePage } from "../core/base-page.js";

export class PageEntryAd extends BasePage {
  PAGE_PATH = "/entry_ad";
  AD_WINDOW = "#modal .modal";
  AD_WINDOW_TITLE = "#modal .modal .modal-title h3";
  AD_WINDOW_PARAGRAPH = "#modal .modal .modal-body p";
  AD_WINDOW_CLOSE_BTN = "#modal .modal .modal-footer p";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getAdWindow() { return this.getElement(this.AD_WINDOW); }
  async getWindowTitle() { return (await this.getElementText(this.AD_WINDOW_TITLE)).trim(); }
  async getWindowParagraph() { return (await this.getElementText(this.AD_WINDOW_PARAGRAPH)).trim(); }
  async getWindowFooter() { return (await this.getElementText(this.AD_WINDOW_CLOSE_BTN)).trim(); }
  getWindowCloseBtn() { return this.getElement(this.AD_WINDOW_CLOSE_BTN); }
  async clickWindowCloseBtn(state = "hidden") {
    await this.clickElement(this.AD_WINDOW_CLOSE_BTN);
    await this.waitForVisibilityChange(this.AD_WINDOW, state);
  }
  async reload() { await this.reloadPage(); }
}
