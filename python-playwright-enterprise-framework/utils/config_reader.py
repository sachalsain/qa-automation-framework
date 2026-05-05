from pathlib import Path

import yaml
from dotenv import load_dotenv


ROOT_DIR = Path(__file__).resolve().parents[1]
CONFIG_PATH = ROOT_DIR / "config" / "config.yaml"
ENV_PATH = ROOT_DIR / ".env"


load_dotenv(ENV_PATH)


def load_config():
    with open(CONFIG_PATH, "r", encoding="utf-8") as config_file:
        return yaml.safe_load(config_file)


def get_config_value(*keys, default=None):
    config = load_config()

    for key in keys:
        if not isinstance(config, dict) or key not in config:
            return default
        config = config[key]

    return config
