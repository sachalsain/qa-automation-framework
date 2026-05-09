import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import dotenv from "dotenv";
import YAML from "yaml";

export const ROOT_DIR = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
export const CONFIG_PATH = path.join(ROOT_DIR, "config", "config.yaml");
export const ENV_PATH = path.join(ROOT_DIR, ".env");

dotenv.config({ path: ENV_PATH });

export function loadConfig() {
  const rawConfig = fs.readFileSync(CONFIG_PATH, "utf-8");
  return YAML.parse(rawConfig);
}

export function getConfigValue(...args) {
  const defaultValue = args.length > 0 ? args[args.length - 1] : undefined;
  const keys = args.slice(0, -1);
  let config = loadConfig();

  for (const key of keys) {
    if (!config || typeof config !== "object" || !(key in config)) {
      return defaultValue;
    }
    config = config[key];
  }

  return config;
}
