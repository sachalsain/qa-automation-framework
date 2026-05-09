import { BasePage } from "../core/base-page.js";

export class PageRedirection extends BasePage {
  PAGE_PATH = "/redirector";
  LINK = "#redirect";
  EXPECTED_URL = "https://the-internet.herokuapp.com/status_codes";
  HEADING_NEW_PAGE = "#content .example h3";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async clickLink() { await this.clickElement(this.LINK); }
  getExpectedUrl() { return this.EXPECTED_URL; }
  getRedirectedUrl() { return this.getUrl(); }
  async getRedirectedHeading() { return (await this.getElementText(this.HEADING_NEW_PAGE)).trim(); }
  async getStatusCode(url) { return (await this.getApiResponse(`${this.EXPECTED_URL}${url}`)).status(); }
}
