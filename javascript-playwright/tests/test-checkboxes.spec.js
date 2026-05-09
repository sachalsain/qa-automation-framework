import { BaseTest, test } from "../src/core/base-test.js";
import { PageCheckboxes } from "../src/pages/page-checkboxes.js";

test("test_page_checkboxes", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const checkboxes = new PageCheckboxes(page);
  await checkboxes.open(base.baseUrl);
  base.assertIntegerMatching(2, await checkboxes.getCheckboxesCount(), "Page should have two checkboxes.");
  await base.assertNotChecked(checkboxes.getCheckbox(1), "First checkbox should be unchecked by default.");
  await base.assertChecked(checkboxes.getCheckbox(2), "Second checkbox should be checked by default.");
  await checkboxes.toggleCheckbox(1);
  await base.assertChecked(checkboxes.getCheckbox(1), "First checkbox should be checked after clicking.");
});
