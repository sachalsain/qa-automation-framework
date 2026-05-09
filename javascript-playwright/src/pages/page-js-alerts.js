import { BasePage } from "../core/base-page.js";

export class PageJSAlerts extends BasePage {
  PAGE_PATH = "/javascript_alerts";
  BTN_ALERT = "text=Click for JS Alert";
  BTN_CONFIRM = "text=Click for JS Confirm";
  BTN_PROMPT = "text=Click for JS Prompt";
  RESULT = "#result";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getMessageOfAlert() { return (await this.getOkDialogMessage(() => this.clickElement(this.BTN_ALERT))).trim(); }
  async getOkMessageOfConfirm() { return (await this.getOkDialogMessage(() => this.clickElement(this.BTN_CONFIRM))).trim(); }
  async getCancelMessageOfConfirm() { return (await this.getCancelDialogMessage(() => this.clickElement(this.BTN_CONFIRM))).trim(); }
  async getOkMessageOfPrompt() { return (await this.getOkDialogMessage(() => this.clickElement(this.BTN_PROMPT))).trim(); }
  async getCancelMessageOfPrompt() { return (await this.getCancelDialogMessage(() => this.clickElement(this.BTN_PROMPT))).trim(); }
  async getResult() { return await this.getElementText(this.RESULT); }
}
