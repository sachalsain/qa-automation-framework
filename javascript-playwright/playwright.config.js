import { defineConfig, devices } from "@playwright/test";
import { loadConfig } from "./src/configurations/configuration-loader.js";

const config = loadConfig(process.env.TB_ENV || "dev");

export default defineConfig({
  testDir: "./tests",
  timeout: config.timeout,
  expect: { timeout: config.timeout },
  fullyParallel: true,
  retries: 1,
  workers: process.env.CI ? 2 : undefined,
  reporter: [
    ["list"],
    ["html", { outputFolder: config.reports_dir || "reports", open: "never" }],
    ["allure-playwright", { resultsDir: config.allure_results_dir || "allure-results/dev" }]
  ],
  use: {
    baseURL: config.base_url,
    headless: config.headless,
    ignoreHTTPSErrors: config.ignore_https_errors,
    acceptDownloads: true,
    viewport: {
      width: config.viewport_width,
      height: config.viewport_height
    },
    actionTimeout: config.timeout,
    navigationTimeout: config.timeout,
    screenshot: "only-on-failure",
    trace: "retain-on-failure"
  },
  projects: [
    {
      name: config.browser || "chromium",
      use: { ...devices["Desktop Chrome"], browserName: config.browser || "chromium" }
    }
  ],
  outputDir: "test-results"
});
