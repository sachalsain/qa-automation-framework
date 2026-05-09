import { BaseTest, test } from "../src/core/base-test.js";
import { PageABTesting } from "../src/pages/page-ab-test.js";

test("test_ab_test_heading_compare_between_browsers", async ({ chromiumPage, firefoxPage, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(chromiumPage, config, testInfo);
  const chromePageAb = new PageABTesting(chromiumPage);
  const firefoxPageAb = new PageABTesting(firefoxPage);
  await chromePageAb.open(base.baseUrl);
  await firefoxPageAb.open(base.baseUrl);
  const chromeHeading = await chromePageAb.getHeaderText();
  const firefoxHeading = await firefoxPageAb.getHeaderText();
  await base.logStep(`Chromium heading: ${chromeHeading}`);
  await base.logStep(`Firefox heading: ${firefoxHeading}`);
  base.assertTextNotNone(chromeHeading, "Chromium heading should not be None");
  base.assertTextNotNone(firefoxHeading, "Firefox heading should not be None");
  base.assertTextDifferent(chromeHeading, firefoxHeading, "Headings should be different");
});
