import { BaseTest, test } from "../src/core/base-test.js";
import { PageJSAlerts } from "../src/pages/page-js-alerts.js";

test("test_page_js_alerts", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const alerts = new PageJSAlerts(page);
  await alerts.open(base.baseUrl);
  base.assertTextMatching("js alert", (await alerts.getMessageOfAlert()).toLowerCase(), "Alert message should match.");
  base.assertTextMatching("successfully clicked", (await alerts.getResult()).toLowerCase(), "Alert result should match.");
  base.assertTextMatching("js confirm", (await alerts.getOkMessageOfConfirm()).toLowerCase(), "Confirm message should match.");
  base.assertTextMatching("ok", (await alerts.getResult()).toLowerCase(), "Confirm OK result should match.");
  base.assertTextMatching("js confirm", (await alerts.getCancelMessageOfConfirm()).toLowerCase(), "Confirm cancel message should match.");
  base.assertTextMatching("cancel", (await alerts.getResult()).toLowerCase(), "Confirm cancel result should match.");
  base.assertTextMatching("js prompt", (await alerts.getOkMessageOfPrompt()).toLowerCase(), "Prompt message should match.");
  base.assertTextMatching("You entered: Playwright Java".toLowerCase(), (await alerts.getResult()).toLowerCase(), "Prompt OK result should match.");
  base.assertTextMatching("js prompt", (await alerts.getCancelMessageOfPrompt()).toLowerCase(), "Prompt cancel message should match.");
  base.assertTextMatching("You entered: null".toLowerCase(), (await alerts.getResult()).toLowerCase(), "Prompt cancel result should match.");
});
