import { BasePage } from "../core/base-page.js";

export class PageFormAuthentication extends BasePage {
  PAGE_PATH = "/login";
  INPUT_USERNAME = "#username";
  INPUT_PASSWORD = "#password";
  BTN_SUBMIT = "#login button[type='submit']";
  MESSAGE = "#flash";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async login(username, password) {
    await this.fillValueInput(this.INPUT_USERNAME, username);
    await this.fillValueInput(this.INPUT_PASSWORD, password);
    await this.clickElement(this.BTN_SUBMIT);
  }
  async getMessage() { return (await this.getElementText(this.MESSAGE)).trim(); }
}
