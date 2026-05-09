import { BasePage } from "../core/base-page.js";

export class PageHover extends BasePage {
  PAGE_PATH = "/hovers";
  PICTURE = ".figure";
  PROFILE = ".figcaption h5";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async hoverOverPicture(pictureIndex) { await this.hoverOn(`${this.PICTURE}:nth-of-type(${pictureIndex})`); }
  async getDisplayedProfileName(pictureIndex) {
    await this.hoverOverPicture(pictureIndex);
    const text = await this.getElementText(`${this.PICTURE}:nth-of-type(${pictureIndex}) ${this.PROFILE}`);
    return text.split(":", 2)[1].trim();
  }
}
