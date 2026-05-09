import path from "node:path";
import { BasePage } from "../core/base-page.js";
import { loadUploadsDir } from "../utilities/path-loader.js";

export class PageFileUpload extends BasePage {
  PAGE_PATH = "/upload";
  BTN_BROWSE = "#file-upload";
  BTN_UPLOAD = "#file-submit";
  SUCCESS_MESSAGE_TITLE = "#content .example h3";
  SUCCESS_MESSAGE_TEXT = "#uploaded-files";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async uploadTestFile(fileName = "uploadTestFile.txt") {
    await this.uploadFile(this.BTN_BROWSE, path.join(loadUploadsDir(), fileName));
    await this.clickElement(this.BTN_UPLOAD);
  }
  async getUploadedMessageTitle() { return (await this.getElementText(this.SUCCESS_MESSAGE_TITLE)).trim(); }
  async getUploadedMessageText() { return (await this.getElementText(this.SUCCESS_MESSAGE_TEXT)).trim(); }
}
