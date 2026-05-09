import { BaseTest, test } from "../src/core/base-test.js";
import { PageDynamicContent } from "../src/pages/page-dynamic-content.js";

test("test_page_dynamic_content", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const dynamicContent = new PageDynamicContent(page);
  await dynamicContent.open(base.baseUrl);
  await base.assertVisible(dynamicContent.getContent(), "Content should be visible.");
  const oldImages = await dynamicContent.getAllImageSrc();
  const oldText = await dynamicContent.getAllText();
  await dynamicContent.reload();
  base.assertListDifferent(oldImages, await dynamicContent.getAllImageSrc(), "Images should change after reload.");
  base.assertListDifferent(oldText, await dynamicContent.getAllText(), "Text should change after reload.");
});
