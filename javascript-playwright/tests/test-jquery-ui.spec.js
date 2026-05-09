import { BaseTest, test } from "../src/core/base-test.js";
import { PageJqueryUi } from "../src/pages/page-jquery-ui.js";

test("test_jquery_ui", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const jqueryUi = new PageJqueryUi(page);
  await jqueryUi.open(base.baseUrl);
  await base.assertVisible(jqueryUi.getMenuDisabled(), "Disabled menu should be visible.");
  await base.assertVisible(jqueryUi.getMenuEnabled(), "Enabled menu should be visible.");
  await jqueryUi.hoverOverEnabled();
  await base.assertVisible(jqueryUi.getMenuDownloads(), "Downloads menu should be visible.");
  await jqueryUi.hoverOverDownloads();
  await base.assertVisible(jqueryUi.getMenuCsv(), "CSV menu should be visible.");
});
