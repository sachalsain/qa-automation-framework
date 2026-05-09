import fs from "node:fs";
import path from "node:path";
import { ROOT_DIR } from "./config_reader.js";

export function loadJson(filePath) {
  const fullPath = path.join(ROOT_DIR, filePath);
  const rawData = fs.readFileSync(fullPath, "utf-8");
  return JSON.parse(rawData);
}
