import { expect, test as base } from "@playwright/test";
import fs from "node:fs";
import path from "node:path";
import { loadConfig } from "./configurations/config_reader.js";
import { ReqResApiService } from "./services/api_services.js";
import { getLogger } from "./utilities/logger.js";
import { ROOT_DIR } from "./utilities/path_loader.js";

const logger = getLogger("fixtures");

export const test = base.extend({
  config: [
    async ({}, use) => {
      const env = process.env.TB_ENV || "dev";
      process.env.TB_ENV = env;
      logger.info("Setting environment variable and loading configuration.");
      logger.debug(`ENV set to '${env}'; loading configurations.`);
      await use(loadConfig(env));
    },
    { scope: "worker" }
  ],

  reqresApi: async ({ request, config }, use, testInfo) => {
    await use(new ReqResApiService(request, config.base_url, config.api_key, testInfo));
  }
});

export { expect };

test.afterEach(async ({ config }, testInfo) => {
  if (testInfo.status === testInfo.expectedStatus) {
    return;
  }

  const errorText = testInfo.error?.stack || testInfo.error?.message || "No failure details were captured.";
  await testInfo.attach("Failure traceback", {
    body: errorText,
    contentType: "text/plain"
  });

  const workerId = process.env.TEST_WORKER_INDEX ? `worker-${process.env.TEST_WORKER_INDEX}` : "main";
  const logFile = path.join(ROOT_DIR, config.log_dir || "logs/dev", `${workerId}.log`);
  if (fs.existsSync(logFile)) {
    await testInfo.attach("Execution log", {
      path: logFile,
      contentType: "text/plain"
    });
  }
});

export async function attachFailureScreenshot(page, config, testInfo) {
  const screenshotDir = path.join(ROOT_DIR, config.screenshot_dir || "screenshots/dev");
  fs.mkdirSync(screenshotDir, { recursive: true });
  const safeName = testInfo.titlePath.join("_").replace(/[^a-zA-Z0-9_.-]/g, "_");
  const screenshotPath = path.join(screenshotDir, `${safeName}.png`);
  await page.screenshot({ path: screenshotPath, fullPage: true });
  await testInfo.attach("Failure screenshot", {
    path: screenshotPath,
    contentType: "image/png"
  });
}
