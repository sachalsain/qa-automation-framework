import { BaseTest, test } from "../src/core/base-test.js";
import { PageFrameInline } from "../src/pages/page-frame-inline.js";

test("test_frame_content", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const frameInline = new PageFrameInline(page);
  await frameInline.open(base.baseUrl);
  base.assertTextMatching("Your content goes here.", await frameInline.getFrameContent(), "Frame content should match.");
});
