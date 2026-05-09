import fs from "node:fs";
import path from "node:path";
import { getConfigValue, ROOT_DIR } from "./config_reader.js";

function timestamp() {
  return new Date().toISOString().replace(/[-:TZ.]/g, "").slice(0, 14);
}

export async function captureScreenshot(page, name) {
  const screenshotDir = getConfigValue("reports", "screenshot_dir", "reports/screenshots") || "reports/screenshots";
  const safeName = name.toLowerCase().trim().replace(/\s+/g, "_");
  const screenshotPath = path.join(ROOT_DIR, screenshotDir, `${safeName}_${timestamp()}.png`);

  fs.mkdirSync(path.dirname(screenshotPath), { recursive: true });
  await page.screenshot({ path: screenshotPath, fullPage: true });

  return screenshotPath;
}
