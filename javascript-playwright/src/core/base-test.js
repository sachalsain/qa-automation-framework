import fs from "node:fs";
import { expect, test as base } from "@playwright/test";
import { loadConfig } from "../configurations/configuration-loader.js";
import { getTestLogger } from "../utilities/logger.js";
import { loadScreenshotsDir } from "../utilities/path-loader.js";

export const test = base.extend({
  config: async ({}, use) => {
    await use(loadConfig(process.env.TB_ENV || "dev"));
  },
  authenticatedPage: async ({ browser, config }, use) => {
    const context = await browser.newContext({
      httpCredentials: {
        username: config.http_username || "admin",
        password: config.http_password || "admin"
      },
      ignoreHTTPSErrors: config.ignore_https_errors,
      acceptDownloads: true,
      viewport: { width: config.viewport_width, height: config.viewport_height }
    });
    const page = await context.newPage();
    page.setDefaultTimeout(config.timeout);
    await use(page);
    await context.close();
  },
  chromiumPage: async ({ playwright, config }, use) => {
    const browser = await playwright.chromium.launch({ headless: config.headless, slowMo: config.slow_mo });
    const context = await browser.newContext({
      ignoreHTTPSErrors: config.ignore_https_errors,
      acceptDownloads: true,
      viewport: { width: config.viewport_width, height: config.viewport_height }
    });
    const page = await context.newPage();
    page.setDefaultTimeout(config.timeout);
    await use(page);
    await context.close();
    await browser.close();
  },
  firefoxPage: async ({ playwright, config }, use) => {
    const browser = await playwright.firefox.launch({ headless: config.headless, slowMo: config.slow_mo });
    const context = await browser.newContext({
      ignoreHTTPSErrors: config.ignore_https_errors,
      acceptDownloads: true,
      viewport: { width: config.viewport_width, height: config.viewport_height }
    });
    const page = await context.newPage();
    page.setDefaultTimeout(config.timeout);
    await use(page);
    await context.close();
    await browser.close();
  },
  geolocationPage: async ({ browser, config }, use) => {
    const context = await browser.newContext({
      ignoreHTTPSErrors: config.ignore_https_errors,
      permissions: ["geolocation"],
      geolocation: {
        latitude: config.geolocation_latitude,
        longitude: config.geolocation_longitude
      },
      viewport: { width: config.viewport_width, height: config.viewport_height }
    });
    const page = await context.newPage();
    page.setDefaultTimeout(config.timeout);
    await use(page);
    await context.close();
  }
});

test.afterEach(async ({ page }, testInfo) => {
  if (testInfo.status !== testInfo.expectedStatus && page) {
    fs.mkdirSync(loadScreenshotsDir(), { recursive: true });
    const path = `${loadScreenshotsDir()}/${testInfo.title.replace(/[^a-zA-Z0-9_.-]/g, "_")}_${Date.now()}.png`;
    await page.screenshot({ path, fullPage: true });
    await testInfo.attach("Failure Screenshot", { path, contentType: "image/png" });
  }
});

export { expect };

export class BaseTest {
  setupTest(page, config, testInfo) {
    this.page = page;
    this.config = config;
    this.testInfo = testInfo;
    this.logger = getTestLogger(testInfo.titlePath.join("::"));
    this.baseUrl = config.base_url;
  }

  async logStep(message) {
    await this.testInfo.attach("Test Step", { body: message, contentType: "text/plain" });
  }

  assertTextNotNone(text, message) {
    expect(text, message).not.toBeNull();
  }

  assertTextDifferent(expectedText, actualText, message) {
    expect(actualText, message).not.toContain(expectedText);
  }

  assertTextMatching(expectedText, actualText, message) {
    expect(actualText, message).toContain(expectedText);
  }

  assertIntegerMatching(expectedValue, actualValue, message) {
    expect(actualValue, message).toBe(expectedValue);
  }

  assertFloatMatching(expectedValue, actualValue, message) {
    expect(actualValue, message).toBeCloseTo(expectedValue, 2);
  }

  async assertVisible(locator, message) {
    await expect(locator, message).toBeVisible();
  }

  async assertHidden(locator, message) {
    await expect(locator, message).toBeHidden();
  }

  async assertEnabled(locator, message) {
    await expect(locator, message).toBeEnabled();
  }

  async assertDisabled(locator, message) {
    await expect(locator, message).toBeDisabled();
  }

  async assertChecked(locator, message) {
    await expect(locator, message).toBeChecked();
  }

  async assertNotChecked(locator, message) {
    await expect(locator, message).not.toBeChecked();
  }

  assertSizeOfList(expectedSize, actualSize, message) {
    expect(actualSize, message).toBe(expectedSize);
  }

  assertApproxSizeOfList(expectedSizes, actualSize, message) {
    expect(expectedSizes, message).toContain(actualSize);
  }

  assertGreater(expectedSize, actualSize, message) {
    expect(actualSize, message).toBeGreaterThan(expectedSize);
  }

  assertListDifferent(oldList, newList, message) {
    expect(newList, message).not.toEqual(oldList);
  }

  assertFileExists(filePath, message) {
    expect(fs.existsSync(filePath), message).toBeTruthy();
  }
}
