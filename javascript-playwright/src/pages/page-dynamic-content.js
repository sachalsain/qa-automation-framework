import { BasePage } from "../core/base-page.js";

export class PageDynamicContent extends BasePage {
  PAGE_PATH = "/dynamic_content";
  CONTENT = "#content .example";
  IMAGE_CONTAINER = "#content > .row img";
  TEXT_CONTAINER = "#content > .row > .large-10";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getContent() { return this.getElement(this.CONTENT); }
  async getAllImageSrc() {
    const images = await this.getElements(this.IMAGE_CONTAINER);
    return await Promise.all(images.map((img) => img.getAttribute("src")));
  }
  async getAllText() {
    const textElements = await this.getElements(this.TEXT_CONTAINER);
    return await Promise.all(textElements.map((text) => text.textContent()));
  }
  async reload() { await this.reloadPage(); }
}
