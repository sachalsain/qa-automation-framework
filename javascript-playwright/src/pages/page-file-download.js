import { BasePage } from "../core/base-page.js";

export class PageFileDownload extends BasePage {
  PAGE_PATH = "/download";
  DOWNLOAD_LINK = "#content .example a:nth-of-type(13)";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async downloadFile() { return await this.clickAndWaitForDownload(this.DOWNLOAD_LINK); }
}
