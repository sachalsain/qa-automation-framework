import fs from "node:fs";
import path from "node:path";
import { ROOT_DIR } from "./config_reader.js";

const LOG_DIR = path.join(ROOT_DIR, "reports", "logs");
const LOG_FILE = path.join(LOG_DIR, "test_execution.log");

function formatMessage(message, args) {
  return args.reduce((formatted, value) => formatted.replace("%s", String(value)), message);
}

export function getLogger(name = "default") {
  fs.mkdirSync(LOG_DIR, { recursive: true });

  function write(level, message, args = []) {
    const line = `${new Date().toISOString()} | ${level} | ${name} | ${formatMessage(message, args)}\n`;
    fs.appendFileSync(LOG_FILE, line, "utf-8");
    process.stdout.write(line);
  }

  return {
    info: (message, ...args) => write("INFO", message, args),
    warn: (message, ...args) => write("WARN", message, args),
    error: (message, ...args) => write("ERROR", message, args),
    debug: (message, ...args) => write("DEBUG", message, args)
  };
}
