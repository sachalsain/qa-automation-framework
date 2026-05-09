import { BaseTest, test } from "../src/core/base-test.js";
import { PageFloatingMenu } from "../src/pages/page-floating-menu.js";

test("test_page_floating_menu", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const floatingMenu = new PageFloatingMenu(page);
  await floatingMenu.open(base.baseUrl);
  await base.assertVisible(floatingMenu.getMenu(), "Floating menu should be visible.");
  await floatingMenu.scrollDown();
  await base.assertVisible(floatingMenu.getMenu(), "Floating menu should remain visible after scroll.");
});
