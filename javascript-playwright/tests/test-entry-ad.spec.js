import { BaseTest, test } from "../src/core/base-test.js";
import { PageEntryAd } from "../src/pages/page-entry-ad.js";

test("test_page_entry_ad", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const entryAd = new PageEntryAd(page);
  await entryAd.open(base.baseUrl);
  await base.assertVisible(entryAd.getAdWindow(), "Entry ad should be visible.");
  base.assertTextMatching("this is a modal window", (await entryAd.getWindowTitle()).toLowerCase(), "Modal title should match.");
  base.assertTextMatching("close", (await entryAd.getWindowFooter()).toLowerCase(), "Footer should contain close.");
  await entryAd.clickWindowCloseBtn("hidden");
  await base.assertHidden(entryAd.getAdWindow(), "Entry ad should be hidden after close.");
});
