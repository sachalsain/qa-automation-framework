import { BaseTest, test } from "../src/core/base-test.js";
import { PageFileUpload } from "../src/pages/page-file-upload.js";

test("test_page_file_upload", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const fileUpload = new PageFileUpload(page);
  await fileUpload.open(base.baseUrl);
  await fileUpload.uploadTestFile();
  base.assertTextMatching("file uploaded", (await fileUpload.getUploadedMessageTitle()).toLowerCase(), "Upload title should match.");
  base.assertTextMatching("uploadTestFile.txt", await fileUpload.getUploadedMessageText(), "Uploaded filename should match.");
});
