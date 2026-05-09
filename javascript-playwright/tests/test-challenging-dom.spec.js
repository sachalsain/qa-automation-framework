import { BaseTest, test } from "../src/core/base-test.js";
import { PageChallengingDOM } from "../src/pages/page-challenging-dom.js";

test("test_challenging_dom_table", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const challengingDom = new PageChallengingDOM(page);
  await challengingDom.open(base.baseUrl);
  base.assertIntegerMatching(7, await challengingDom.getTableHeadersCount(), "Table should have 7 headers.");
  base.assertIntegerMatching(10, await challengingDom.getTableDataRowCount(), "Table should have 10 data rows.");
  base.assertTextMatching("Adipisci1", await challengingDom.getTableDataValue(2, 3), "Expected cell value to be Adipisci1.");
});
