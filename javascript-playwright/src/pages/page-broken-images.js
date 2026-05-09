import { BasePage } from "../core/base-page.js";

export class PageBrokenImages extends BasePage {
  PAGE_PATH = "/broken_images";
  IMAGE_CONTAINER = ".example";
  IMAGES_LIST = ".example img";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  getImageContainer() { return this.getElement(this.IMAGE_CONTAINER); }
  async getAllImages() { return await this.getElements(this.IMAGES_LIST); }
  async runJsToCheckImgComplete(img, script) { return await this.executeScript(img, script); }
  async getStatusCode(baseUrl, imageSrc) { return await this.getApiResponse(`${baseUrl}/${imageSrc}`); }
}
