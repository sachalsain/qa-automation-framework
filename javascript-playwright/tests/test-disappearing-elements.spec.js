import { BaseTest, test } from "../src/core/base-test.js";
import { PageDisappearingElements } from "../src/pages/page-disappearing-elements.js";

test("test_page_disappearing_elements", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const disappearing = new PageDisappearingElements(page);
  await disappearing.open(base.baseUrl);
  await base.assertVisible(disappearing.getMenu(), "Menu should be visible.");
  base.assertApproxSizeOfList([4, 5], await disappearing.getListCount(), "Menu list should have expected items.");
});
