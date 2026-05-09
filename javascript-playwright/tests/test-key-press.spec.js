import { BaseTest, test } from "../src/core/base-test.js";
import { PageKeyPress } from "../src/pages/page-key-press.js";

test("test_key_press", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const keyPress = new PageKeyPress(page);
  await keyPress.open(base.baseUrl);
  await keyPress.clickInput();
  await keyPress.pressKey("a");
  base.assertTextMatching(": a", (await keyPress.getResult()).toLowerCase(), "Result should display a.");
  await keyPress.pressKey("ArrowDown");
  base.assertTextMatching(": down", (await keyPress.getResult()).toLowerCase(), "Result should display down.");
});
