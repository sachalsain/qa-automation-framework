import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const configs = new Map();

export function getEnv() {
  return (process.env.TB_ENV || "dev").toLowerCase();
}

export function loadConfig(env = getEnv()) {
  const activeEnv = String(env).toLowerCase();

  if (!configs.has(activeEnv)) {
    const configFile = path.join(__dirname, `${activeEnv}.json`);

    if (!fs.existsSync(configFile)) {
      throw new Error(`Config file not found for environment: ${activeEnv}`);
    }

    configs.set(activeEnv, JSON.parse(fs.readFileSync(configFile, "utf-8")));
  }

  return configs.get(activeEnv);
}

export function getConfigValue(key, defaultValue = undefined) {
  const config = loadConfig();
  return Object.prototype.hasOwnProperty.call(config, key) ? config[key] : defaultValue;
}
