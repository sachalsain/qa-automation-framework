import fs from "node:fs";
import path from "node:path";
import { getConfigValue } from "../configurations/config_reader.js";
import { ROOT_DIR } from "./path_loader.js";

const levels = { DEBUG: 10, INFO: 20, WARN: 30, ERROR: 40 };

function toLogLevel(level, defaultLevel = "DEBUG") {
  const normalized = String(level || defaultLevel).toUpperCase();
  return levels[normalized] ? normalized : defaultLevel;
}

function shouldLog(configuredLevel, messageLevel) {
  return levels[messageLevel] >= levels[toLogLevel(configuredLevel, "INFO")];
}

function parseMaxBytes(value, defaultValue = 5 * 1024 * 1024) {
  if (Number.isInteger(value)) {
    return value;
  }

  if (typeof value === "string") {
    const sanitized = value.replace(/\s/g, "");
    if (/^\d+(?:\*\d+)*$/.test(sanitized)) {
      return sanitized.split("*").reduce((result, chunk) => result * Number(chunk), 1);
    }
    if (/^\d+$/.test(sanitized)) {
      return Number(sanitized);
    }
  }

  return defaultValue;
}

function sanitizeName(name) {
  return String(name).replace(/[^a-zA-Z0-9_.-]/g, "_");
}

function getWorkerId() {
  return process.env.TEST_WORKER_INDEX ? `worker-${process.env.TEST_WORKER_INDEX}` : "main";
}

function formatLine(level, name, message) {
  return `${new Date().toISOString()} | ${level.padEnd(8)} | ${process.pid} | ${name} | ${message}\n`;
}

function rotateIfNeeded(logFile, maxBytes, backupCount) {
  if (!fs.existsSync(logFile) || fs.statSync(logFile).size <= maxBytes) {
    return;
  }

  for (let index = backupCount - 1; index >= 1; index -= 1) {
    const source = `${logFile}.${index}`;
    const target = `${logFile}.${index + 1}`;
    if (fs.existsSync(source)) {
      fs.renameSync(source, target);
    }
  }

  fs.renameSync(logFile, `${logFile}.1`);
}

export function getLogger(name) {
  const fileLevel = getConfigValue("log_level", "INFO");
  const consoleLevel = getConfigValue("console_log_level", "INFO");
  const logDir = path.join(ROOT_DIR, getConfigValue("log_dir", "logs/dev"));
  const logFile = path.join(logDir, `${getWorkerId()}.log`);
  const maxBytes = parseMaxBytes(getConfigValue("max_file_size", 5 * 1024 * 1024));
  const backupCount = Number(getConfigValue("max_old_files_num", 5));
  const encoding = getConfigValue("log_encoding", "utf-8");

  fs.mkdirSync(logDir, { recursive: true });

  function write(level, message) {
    const line = formatLine(level, name, message);

    if (shouldLog(fileLevel, level)) {
      rotateIfNeeded(logFile, maxBytes, backupCount);
      fs.appendFileSync(logFile, line, encoding);
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
