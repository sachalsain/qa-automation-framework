import { BaseTest, test } from "../src/core/base-test.js";
import { PageDropdown } from "../src/pages/page-dropdown.js";

test("test_page_dropdown", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const dropdown = new PageDropdown(page);
  await dropdown.open(base.baseUrl);
  await dropdown.selectValue("1");
  base.assertTextMatching("Option 1", await dropdown.getSelectedValue(), "Dropdown should show Option 1.");
  await dropdown.selectValue("2");
  base.assertTextMatching("Option 2", await dropdown.getSelectedValue(), "Dropdown should show Option 2.");
});
