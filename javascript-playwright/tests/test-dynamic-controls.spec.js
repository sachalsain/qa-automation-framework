import { BaseTest, test } from "../src/core/base-test.js";
import { PageDynamicControls } from "../src/pages/page-dynamic-controls.js";

test("test_page_dynamic_controls", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const controls = new PageDynamicControls(page);
  await controls.open(base.baseUrl);
  await base.assertVisible(controls.getCheckbox(), "Checkbox should be visible.");
  await controls.clickCheckboxBtn("hidden");
  await base.assertHidden(controls.getCheckbox(), "Checkbox should be hidden after remove.");
  base.assertTextMatching("gone", (await controls.getCheckboxMsg()).toLowerCase(), "Checkbox message should contain gone.");
  await controls.clickCheckboxBtn("hidden");
  await base.assertVisible(controls.getCheckbox(), "Checkbox should be visible after add.");
  await base.assertDisabled(controls.getTextbox(), "Textbox should be disabled by default.");
  await controls.clickTextboxBtn("left", "enabled");
  await base.assertEnabled(controls.getTextbox(), "Textbox should be enabled.");
  await controls.enterValueInTextbox("Playwright JavaScript");
  base.assertTextMatching("Playwright JavaScript", await controls.getTextboxValue(), "Textbox value should match.");
});
