import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { getConfigValue } from "../configurations/configuration-loader.js";

export const ROOT_DIR = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..", "..");

function resolveDir(configKey, defaultPath) {
  const directory = path.join(ROOT_DIR, String(getConfigValue(configKey, defaultPath)));
  fs.mkdirSync(directory, { recursive: true });
  return directory;
}

export const loadScreenshotsDir = () => resolveDir("screenshot_dir", "screenshots/dev");
export const loadAllureResultsDir = () => resolveDir("allure_results_dir", "allure-results/dev");
export const loadReportsDir = () => resolveDir("reports_dir", "reports");
export const loadDownloadsDir = () => resolveDir("downloads_dir", "downloads");
export const loadUploadsDir = () => resolveDir("uploads_dir", "uploads");
export const loadDataFile = (file) => path.join(resolveDir("data_dir", "data"), file);
