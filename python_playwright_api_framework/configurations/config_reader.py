# Created on : Mar 28, 2026, 4:12:57 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}


import json
import os
from pathlib import Path


class ConfigurationReader:
    _configs: dict[str, dict] = {}

    @classmethod
    def _get_env(cls) -> str:
        # Return the active environment name as a string
        return os.getenv("TB_ENV", "dev").lower()

    # Define a class method that loads the configuration file for the current environment.
    @classmethod
    def load_config(cls):
        # Get the current environment name.
        env = cls._get_env()
        # Check whether this environment’s config has already been loaded.
        if env not in cls._configs:
            # If it has already been loaded, the method skips reading the file again and uses the cached version.
            # Build the path to the JSON config file.
            config_file = Path(__file__).parent / f"{env}.json"
            # Check whether the config file actually exists.
            if not config_file.exists():
                # Raise an error if the file is missing.
                raise FileNotFoundError(f"Config file not found for environment: {env}")
            # Open the config file for reading
            with open(config_file, "r", encoding="utf-8") as file:
                # Read the JSON file and converts it into a Python dictionary.
                cls._configs[env] = json.load(file)
        # Return the loaded configuration dictionary for the current environment.
        return cls._configs[env]

    # Define a helper method to fetch a single config value by key.
    @classmethod
    def get(cls, key, default=None):
        # Load the current environment config, or retrieve it from cache if already loaded.
        config = cls.load_config()
        # Return the value for key. If the key does not exist, return default.
        return config.get(key, default)

    # A public method to return the current environment name.
    @classmethod
    def get_env(cls) -> str:
        return cls._get_env()
