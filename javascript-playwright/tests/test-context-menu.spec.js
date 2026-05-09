import { BaseTest, test } from "../src/core/base-test.js";
import { PageContextMenu } from "../src/pages/page-context-menu.js";

test("test_page_context_menu", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const contextMenu = new PageContextMenu(page);
  await contextMenu.open(base.baseUrl);
  await base.assertVisible(contextMenu.getHotSpot(), "Hot spot must be visible.");
  base.assertTextMatching("context menu", (await contextMenu.getTextOfAlert()).toLowerCase(), "Expected context menu alert.");
});
