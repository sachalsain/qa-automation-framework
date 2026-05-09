import fs from "node:fs";
import path from "node:path";
import { getConfigValue } from "../configurations/configuration-loader.js";
import { ROOT_DIR } from "./path-loader.js";

const levels = { DEBUG: 10, INFO: 20, WARN: 30, ERROR: 40 };

function parseMaxBytes(value, defaultValue = 5 * 1024 * 1024) {
  if (Number.isInteger(value)) return value;
  if (typeof value === "string" && /^\d+(?:\s*\*\s*\d+)*$/.test(value)) {
    return value.split("*").map((chunk) => Number(chunk.trim())).reduce((a, b) => a * b, 1);
  }
  return defaultValue;
}

function shouldLog(configured, level) {
  return levels[level] >= levels[String(configured || "INFO").toUpperCase()];
}

function sanitizeName(name) {
  return name.replace(/[^a-zA-Z0-9_.-]/g, "_");
}

export function getLogger(name) {
  const logLevel = getConfigValue("log_level", "INFO");
  const consoleLevel = getConfigValue("console_log_level", "INFO");
  const logDir = path.join(ROOT_DIR, getConfigValue("log_dir", "logs/dev"));
  fs.mkdirSync(logDir, { recursive: true });
  const worker = process.env.TEST_WORKER_INDEX ? `worker-${process.env.TEST_WORKER_INDEX}` : "main";
  const logFile = path.join(logDir, `${worker}.log`);
  const maxBytes = parseMaxBytes(getConfigValue("max_file_size", 5 * 1024 * 1024));

  function write(level, message) {
    const line = `${new Date().toISOString()} | ${level.padEnd(8)} | ${name} | ${message}\n`;
    if (shouldLog(logLevel, level)) {
      if (fs.existsSync(logFile) && fs.statSync(logFile).size > maxBytes) {
        fs.renameSync(logFile, `${logFile}.${Date.now()}.bak`);
      }
      fs.appendFileSync(logFile, line, getConfigValue("log_encoding", "utf-8"));
    }
    if (shouldLog(consoleLevel, level)) {
      process.stdout.write(line);
    }
  }

  return {
    debug: (message) => write("DEBUG", message),
    info: (message) => write("INFO", message),
    warn: (message) => write("WARN", message),
    error: (message) => write("ERROR", message)
  };
}

export function getTestLogger(testId) {
  return getLogger(`test.${sanitizeName(testId)}`);
}
