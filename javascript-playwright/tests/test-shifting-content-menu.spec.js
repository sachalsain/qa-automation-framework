import { BaseTest, test } from "../src/core/base-test.js";
import { PageShiftingContentMenu } from "../src/pages/page-shifting-content-menu.js";

test("test_shifting_content_menu", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const shiftingContentMenu = new PageShiftingContentMenu(page);
  await shiftingContentMenu.open(base.baseUrl);
  base.assertTextMatching("mode", await shiftingContentMenu.clickLink(1), "URL should contain mode.");
  base.assertTextMatching("pixel_shift", await shiftingContentMenu.clickLink(2), "URL should contain pixel_shift.");
  base.assertTextMatching("pixel_shift", await shiftingContentMenu.clickLink(3), "URL should contain mode and pixel_shift.");
});
