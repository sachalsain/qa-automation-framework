import { defineConfig, devices } from "@playwright/test";
import { getConfigValue } from "./utils/config_reader.js";

const browserName = getConfigValue("browser", "name", "chromium");
const timeout = getConfigValue("browser", "timeout", 30000);
const testTimeout = Math.max(timeout * 6, 30000);
const workers = getConfigValue("browser", "workers", 2);
const locale = getConfigValue("browser", "locale", "en-US");
const viewportWidth = getConfigValue("browser", "viewport", "width", 1366);
const viewportHeight = getConfigValue("browser", "viewport", "height", 768);

export default defineConfig({
  testDir: "./tests",
  timeout: testTimeout,
  expect: { timeout },
  fullyParallel: true,
  workers: process.env.CI ? Math.min(workers, 2) : workers,
  reporter: [
    ["list"],
    ["html", { outputFolder: "reports/playwright-report", open: "never" }],
    ["allure-playwright", { resultsDir: "reports/allure-results" }]
  ],
  use: {
    baseURL: getConfigValue("app", "base_url", "https://opensource-demo.orangehrmlive.com/"),
    headless: getConfigValue("browser", "headless", true),
    locale,
    extraHTTPHeaders: {
      "Accept-Language": `${locale},en;q=0.9`
    },
    viewport: {
      width: viewportWidth,
      height: viewportHeight
    },
    actionTimeout: timeout,
    navigationTimeout: timeout,
    screenshot: "only-on-failure",
    trace: "retain-on-failure",
    video: "retain-on-failure"
  },
  projects: [
    {
      name: browserName,
      use: {
        ...devices["Desktop Chrome"],
        browserName
      }
    }
  ],
  outputDir: "test-results"
});
