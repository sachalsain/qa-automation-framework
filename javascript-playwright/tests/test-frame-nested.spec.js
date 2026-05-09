import { BaseTest, test } from "../src/core/base-test.js";
import { PageFrameNested } from "../src/pages/page-frame-nested.js";

test("test_frame_content", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const frameNested = new PageFrameNested(page);
  await frameNested.open(base.baseUrl);
  base.assertTextMatching("LEFT", await frameNested.getFrameContent("left"), "Left frame should match.");
  base.assertTextMatching("MIDDLE", await frameNested.getFrameContent("middle"), "Middle frame should match.");
  base.assertTextMatching("RIGHT", await frameNested.getFrameContent("right"), "Right frame should match.");
  base.assertTextMatching("BOTTOM", await frameNested.getFrameContent("bottom"), "Bottom frame should match.");
});
