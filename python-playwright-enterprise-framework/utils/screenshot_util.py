from datetime import datetime
from pathlib import Path

from utils.config_reader import get_config_value


ROOT_DIR = Path(__file__).resolve().parents[1]


def capture_screenshot(page, name):
    screenshot_dir = get_config_value(
        "reports",
        "screenshot_dir",
        default="reports/screenshots",
    ) or "reports/screenshots"

    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    safe_name = name.lower().strip().replace(" ", "_")
    screenshot_path = ROOT_DIR / Path(screenshot_dir) / f"{safe_name}_{timestamp}.png"

    screenshot_path.parent.mkdir(parents=True, exist_ok=True)
    page.screenshot(path=str(screenshot_path), full_page=True)

    return screenshot_path
