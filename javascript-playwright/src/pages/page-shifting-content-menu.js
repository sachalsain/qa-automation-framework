import { BasePage } from "../core/base-page.js";

export class PageShiftingContentMenu extends BasePage {
  PAGE_PATH = "/shifting_content/menu";
  LINK1 = ".example  p:nth-child(3) a";
  LINK2 = ".example  p:nth-child(4) a";
  LINK3 = ".example  p:nth-child(5) a";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async clickLink(value) {
    if (value === 1) await this.clickElement(this.LINK1);
    else if (value === 2) await this.clickElement(this.LINK2);
    else if (value === 3) await this.clickElement(this.LINK3);
    else throw new Error("Link not found");
    return this.getUrl();
  }
}
