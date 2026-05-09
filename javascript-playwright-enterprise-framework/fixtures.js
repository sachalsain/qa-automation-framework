import { test as base } from "@playwright/test";
import { captureScreenshot } from "./utils/screenshot_util.js";

export const test = base.extend({});

test.afterEach(async ({ page }, testInfo) => {
  if (testInfo.status !== testInfo.expectedStatus && page) {
    const screenshotPath = await captureScreenshot(page, testInfo.title);
    await testInfo.attach("Failure Screenshot", {
      path: screenshotPath,
      contentType: "image/png"
    });
  }
});

test.beforeEach(async ({ page }) => {
  page.setDefaultTimeout(test.info().project.use.actionTimeout || 30000);
});

export { expect } from "@playwright/test";
