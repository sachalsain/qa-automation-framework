import { BasePage } from "../core/base-page.js";

export class PageFrameInline extends BasePage {
  PAGE_PATH = "/iframe";
  INLINE_FRAME = "#mce_0_ifr";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getFrameContent() { return await this.getFrameText(this.INLINE_FRAME); }
}
