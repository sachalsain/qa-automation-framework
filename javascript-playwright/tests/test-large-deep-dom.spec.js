import { BaseTest, test } from "../src/core/base-test.js";
import { PageLargeDeepDom } from "../src/pages/page-large-deep-dom.js";

test("test_large_deep_dom", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const largeDeepDom = new PageLargeDeepDom(page);
  await largeDeepDom.open(base.baseUrl);
  await base.assertVisible(largeDeepDom.getTable(), "The table should be visible.");
  base.assertTextMatching("10.5", await largeDeepDom.getValueOfCell(10, 5), "The value of the cell should be 10.5.");
});
