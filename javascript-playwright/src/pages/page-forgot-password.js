import { BasePage } from "../core/base-page.js";

export class PageForgotPassword extends BasePage {
  PAGE_PATH = "/forgot_password";
  INPUT_EMAIL = "#email";
  BTN_SUBMIT = "#form_submit[type='submit']";
  MESSAGE = "h1";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async submitEmail(email = "qa@example.com") {
    await this.fillValueInput(this.INPUT_EMAIL, email);
    await this.clickElement(this.BTN_SUBMIT);
  }
  async getMessage() { return (await this.getElementText(this.MESSAGE)).trim(); }
  getPageUrl() { return this.getUrl(); }
  async getStatusCode(url) { return await this.getApiResponse(url); }
}
