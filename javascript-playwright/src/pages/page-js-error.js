import { BasePage } from "../core/base-page.js";

export class PageJSError extends BasePage {
  PAGE_PATH = "/javascript_error";

  constructor(page) {
    super(page);
    this.errors = [];
  }

  async open(baseUrl) {
    this.errors = [];
    const [error] = await Promise.all([
      this.page.waitForEvent("pageerror"),
      this.navigate(`${baseUrl}${this.PAGE_PATH}`)
    ]);
    this.errors.push(error);
  }

  getErrors() { return this.errors; }
}
