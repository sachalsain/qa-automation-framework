import { BaseTest, test } from "../src/core/base-test.js";
import { PageDynamicElements } from "../src/pages/page-dynamic-elements.js";

test("test_page_dynamic_elements_hidden", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const dynamic = new PageDynamicElements(page);
  await dynamic.open(base.baseUrl, 1);
  await base.assertVisible(dynamic.getStartBtn(), "Start button should be visible.");
  await dynamic.clickStartBtn("hidden");
  await base.assertVisible(dynamic.getMessage(), "Finish message should be visible.");
});

test("test_page_dynamic_elements_insert", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const dynamic = new PageDynamicElements(page);
  await dynamic.open(base.baseUrl, 2);
  await dynamic.clickStartBtn("hidden");
  await base.assertVisible(dynamic.getMessage(), "Finish message should be visible.");
});
