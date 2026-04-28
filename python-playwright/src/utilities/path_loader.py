# Created on : Mar 28, 2026, 4:34:58 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

from pathlib import Path

from src.configurations.configuration_loader import ConfigurationLoader
from src.utilities.logger import get_logger

logger = get_logger(__name__)

ROOT_DIR = Path(__file__).resolve().parent.parent.parent


def _resolve_dir(config_key: str, default: str) -> Path:
    logger.info(f"Resolving directory for config key: '{config_key}' with default: '{default}'")
    directory = ROOT_DIR / str(ConfigurationLoader.get(config_key, default))
    logger.debug(f"Creating directory if it does not exist: {directory}")
    directory.mkdir(parents=True, exist_ok=True)
    logger.debug("Returning resolved directory.")
    return directory


def load_screenshots_dir() -> Path:
    logger.info("Loading screenshots directory.")
    return _resolve_dir("screenshot_dir", "screenshots/dev")


def load_allure_results_dir() -> Path:
    logger.info("Loading Allure results directory.")
    return _resolve_dir("allure_results_dir", "allure-results")


def load_reports_dir() -> Path:
    logger.info("Loading reports directory.")
    return _resolve_dir("reports_dir", "reports")


def load_downloads_dir() -> Path:
    logger.info("Loading downloads directory.")
    return _resolve_dir("downloads_dir", "downloads")


def load_uploads_dir() -> Path:
    logger.info("Loading uploads directory.")
    return _resolve_dir("uploads_dir", "uploads")


def load_data_dir(file: str) -> Path:
    logger.info("Loading data directory.")
    return _resolve_dir("data_dir", "data") / file

