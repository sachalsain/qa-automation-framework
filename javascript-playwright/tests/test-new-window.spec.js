import { BaseTest, test } from "../src/core/base-test.js";
import { PageNewWindow } from "../src/pages/page-new-window.js";

test("test_new_window", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const newWindow = new PageNewWindow(page);
  await newWindow.open(base.baseUrl);
  const openedPage = await newWindow.clickToCreateNewPage();
  base.assertTextMatching("new window", (await newWindow.getNewPageText(openedPage)).toLowerCase(), "New window heading should match.");
});
