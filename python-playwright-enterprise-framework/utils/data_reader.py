import json
from pathlib import Path


ROOT_DIR = Path(__file__).resolve().parents[1]


def load_json(file_path):
    full_path = ROOT_DIR / file_path

    with open(full_path, "r", encoding="utf-8") as json_file:
        return json.load(json_file)
