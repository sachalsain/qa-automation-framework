import { BaseTest, test } from "../src/core/base-test.js";
import { PageJSError } from "../src/pages/page-js-error.js";

test("test_page_js_error", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const jsError = new PageJSError(page);
  await jsError.open(base.baseUrl);
  base.assertGreater(0, jsError.getErrors().length, "There should be at least one JS error.");
  for (const error of jsError.getErrors()) {
    base.assertTextMatching("undefined", String(error).toLowerCase(), "Error should contain undefined.");
  }
});
