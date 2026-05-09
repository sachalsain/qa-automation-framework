import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const configs = new Map();

export function getEnv() {
  return (process.env.TB_ENV || "dev").toLowerCase();
}

export function loadConfig(env = getEnv()) {
  if (!configs.has(env)) {
    const configPath = path.join(__dirname, `${env}.json`);
    if (!fs.existsSync(configPath)) {
      throw new Error(`Config file not found for environment: ${env}`);
    }
    configs.set(env, JSON.parse(fs.readFileSync(configPath, "utf-8")));
  }
  return configs.get(env);
}

export function getConfigValue(key, defaultValue = undefined) {
  const config = loadConfig();
  return Object.prototype.hasOwnProperty.call(config, key) ? config[key] : defaultValue;
}
