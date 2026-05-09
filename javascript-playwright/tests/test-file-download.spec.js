import { BaseTest, test } from "../src/core/base-test.js";
import { PageFileDownload } from "../src/pages/page-file-download.js";

test("test_page_file_download", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const fileDownload = new PageFileDownload(page);
  await fileDownload.open(base.baseUrl);
  base.assertFileExists(await fileDownload.downloadFile(), "Downloaded file should exist.");
});
