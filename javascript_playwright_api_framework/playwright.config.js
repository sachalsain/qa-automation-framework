import { defineConfig } from "@playwright/test";
import { loadConfig } from "./configurations/config_reader.js";

const config = loadConfig(process.env.TB_ENV || "dev");

export default defineConfig({
  testDir: "./tests",
  timeout: config.timeout,
  expect: {
    timeout: config.timeout
  },
  fullyParallel: true,
  retries: 0,
  workers: process.env.CI ? 2 : undefined,
  reporter: [
    ["list"],
    ["html", { outputFolder: `${config.reports_dir || "reports"}/playwright-report`, open: "never" }],
    ["allure-playwright", { resultsDir: `${config.reports_dir || "reports"}/allure-results` }]
  ],
  use: {
    baseURL: config.base_url,
    extraHTTPHeaders: {
      "x-api-key": config.api_key,
      "Content-Type": "application/json"
    },
    ignoreHTTPSErrors: config.ignore_https_errors,
    viewport: {
      width: config.viewport_width,
      height: config.viewport_height
    },
    actionTimeout: config.timeout,
    navigationTimeout: config.timeout,
    screenshot: "only-on-failure",
    trace: "retain-on-failure"
  },
  outputDir: "test-results"
});
