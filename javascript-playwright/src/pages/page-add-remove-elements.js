import { BasePage } from "../core/base-page.js";

export class PageAddRemoveElements extends BasePage {
  PAGE_PATH = "/add_remove_elements/";
  BTN_ADD = "button[onclick='addElement()']";
  BTN_DELETE = "button[onclick='deleteElement()']";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getAddButton() { return this.getElement(this.BTN_ADD); }
  getDeleteButton() { return this.getElement(this.BTN_DELETE); }
  async clickAddButton() { await this.clickElement(this.BTN_ADD); }
  async clickDeleteButton() { await this.clickElement(this.BTN_DELETE); }
}
