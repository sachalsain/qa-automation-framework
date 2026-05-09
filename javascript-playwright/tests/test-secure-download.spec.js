import { BaseTest, test } from "../src/core/base-test.js";
import { PageSecureDownload } from "../src/pages/page-secure-download.js";

test("test_secure_download", async ({ authenticatedPage, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(authenticatedPage, config, testInfo);
  const secureDownload = new PageSecureDownload(authenticatedPage);
  await secureDownload.open(base.baseUrl);
  base.assertTextMatching("file downloader", (await secureDownload.getRedirectedHeading()).toLowerCase(), "Heading should contain file downloader.");
  base.assertFileExists(await secureDownload.downloadFile(), "Downloaded secure file should exist.");
});
