import { BasePage } from "../core/base-page.js";

export class PageGeolocation extends BasePage {
  PAGE_PATH = "/geolocation";
  BTN_LOCATION = "button:text('Where am I?')";
  LATITUDE = "#lat-value";
  LONGITUDE = "#long-value";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async clickLocationBtn() {
    await this.clickElement(this.BTN_LOCATION);
    await this.waitForVisibilityChange(this.LATITUDE, "visible");
  }
  async getGeolocation() {
    return [
      Number((await this.getElementText(this.LATITUDE)).trim()),
      Number((await this.getElementText(this.LONGITUDE)).trim())
    ];
  }
}
