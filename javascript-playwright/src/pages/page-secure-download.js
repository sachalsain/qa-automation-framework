import { PageFileDownload } from "./page-file-download.js";

export class PageSecureDownload extends PageFileDownload {
  PAGE_PATH = "/download_secure";
  HEADING_NEW_PAGE = "#content .example h3";

  async getRedirectedHeading() { return (await this.getElementText(this.HEADING_NEW_PAGE)).trim(); }
}
