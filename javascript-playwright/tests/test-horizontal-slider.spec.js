import { BaseTest, test } from "../src/core/base-test.js";
import { PageHorizontalSlider } from "../src/pages/page-horizontal-slider.js";

test("test_horizontal_slider", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const slider = new PageHorizontalSlider(page);
  await slider.open(base.baseUrl);
  await slider.setSliderValue(3.5);
  base.assertFloatMatching(3.5, await slider.getSliderValue(), "Slider value should match.");
});
