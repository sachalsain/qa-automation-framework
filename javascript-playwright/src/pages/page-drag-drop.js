import { BasePage } from "../core/base-page.js";

export class PageDragDrop extends BasePage {
  PAGE_PATH = "/drag_and_drop";
  BOX_A = "#column-a";
  BOX_B = "#column-b";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async performDragDrop() { await this.dragDrop(this.BOX_A, this.BOX_B); }
  async getTextOfBoxA() { return await this.getElementText(this.BOX_A); }
  async getTextOfBoxB() { return await this.getElementText(this.BOX_B); }
}
