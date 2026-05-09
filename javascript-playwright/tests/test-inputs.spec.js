import { BaseTest, test } from "../src/core/base-test.js";
import { PageInputs } from "../src/pages/page-inputs.js";

test("test_inputs", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const inputs = new PageInputs(page);
  await inputs.open(base.baseUrl);
  await inputs.fillNumber(123);
  base.assertTextMatching("123", await inputs.getValue(), "Input value should match.");
});
