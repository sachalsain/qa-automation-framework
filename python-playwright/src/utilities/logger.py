# Created on : Mar 28, 2026, 4:10:12 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}


import logging
from logging.handlers import RotatingFileHandler
import os
from pathlib import Path
import re

from src.configurations.configuration_loader import ConfigurationLoader


def _to_log_level(level_str: str | None, default=logging.INFO) -> int:
    if not level_str:
        return default
    return getattr(logging, str(level_str).upper(), default)


def _get_worker_id() -> str:
    return os.getenv("PYTEST_XDIST_WORKER", "main")


def _sanitize_name(name: str) -> str:
    return "".join(c if c.isalnum() or c in ("-", "_", ".") else "_" for c in name)


def _build_formatter() -> logging.Formatter:
    return logging.Formatter(
        "%(asctime)s | %(levelname)-8s | %(processName)s | %(threadName)s | %(name)s | %(message)s"
    )


def _parse_max_bytes(value, default: int = 5 * 1024 * 1024) -> int:
    if isinstance(value, int):
        return value

    if isinstance(value, str):
        sanitized = value.replace(" ", "")
        if re.fullmatch(r"\d+(?:\*\d+)*", sanitized):
            result = 1
            for chunk in sanitized.split("*"):
                result *= int(chunk)
            return result

        if sanitized.isdigit():
            return int(sanitized)

    return default


def get_logger(name: str) -> logging.Logger:
    """
    Shared logger for framework/services/pages/modules.

    Uses one log file per worker:
      logs/<env>/<worker>.log
    """
    logger = logging.getLogger(name)

    if not logger.handlers:
        file_level = _to_log_level(ConfigurationLoader.get("log_level", "INFO"))
        console_level = _to_log_level(ConfigurationLoader.get("console_log_level", "INFO"))
        log_dir = Path(str(ConfigurationLoader.get("log_dir", "logs/dev")))
        worker_id = _get_worker_id()
        logger.setLevel(logging.INFO)
        
        log_dir.mkdir(parents=True, exist_ok=True)
        log_file = log_dir / f"{worker_id}.log"

        logger.setLevel(min(file_level, console_level))
        # logger.propagate = False
        logger.propagate = True

        # console_handler = logging.StreamHandler()
        formatter = _build_formatter()
        # console_handler.setLevel(console_level)
        # console_handler.setFormatter(formatter)
        file_handler = RotatingFileHandler(
            filename = log_file,
            maxBytes = _parse_max_bytes(ConfigurationLoader.get("max_file_size", 5 * 1024 * 1024)),
            backupCount = int(ConfigurationLoader.get("max_old_files_num") or 5),
            encoding = ConfigurationLoader.get("log_encoding", "utf-8"),
        )
        file_handler.setLevel(file_level)
        file_handler.setFormatter(formatter)
        # logger.addHandler(console_handler)
        logger.addHandler(file_handler)

    return logger


def get_test_logger(nodeid: str) -> logging.Logger:
    """
    Test-specific logger.
    Logger name is based on the pytest nodeid so logs are easier to trace.

    Example:
      tests/test_login.py::test_valid_login
    """
    safe_name = _sanitize_name(nodeid)
    return get_logger(f"test.{safe_name}")
