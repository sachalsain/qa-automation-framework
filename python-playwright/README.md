# Playwright Python Framework

## Features

- Playwright with Python
- Pytest test framework
- Page Object Model
- Base test setup and reusable fixtures
- Browser execution using `pytest-playwright`
- Configuration using environment-specific JSON files
- Environment switching using `--env`
- Allure reporting
- Screenshot capture on test failure
- Retry logic with `pytest-rerunfailures`
- Parallel execution using `pytest-xdist`
- Test markers for smoke and regression
- JSON test data support
- File upload and download support
- Logging using custom logger utilities

## Project Structure

```text
python-playwright
|-- README.md
|-- requirements.txt
|-- pytest.ini
|-- pyproject.toml
|-- conftest.py
|-- main.py
|-- src
│   |-- configurations
│   │   |-- configuration_loader.py
│   │   `-- dev.json
│   |-- core
│   │   |-- base_page.py
│   │   `-- base_test.py
│   |-- pages
│   │   |-- page_ab_test.py
│   │   |-- page_add_remove_elements.py
│   │   |-- page_basic_auth.py
│   │   |-- page_broken_images.py
│   │   |-- page_challenging_dom.py
│   │   |-- page_checkboxes.py
│   │   |-- page_context_menu.py
│   │   |-- page_digest_authentication.py
│   │   |-- page_disappearing_elements.py
│   │   |-- page_drag_drop.py
│   │   |-- page_dropdown.py
│   │   |-- page_dynamic_content.py
│   │   |-- page_dynamic_controls.py
│   │   |-- page_dynamic_elements.py
│   │   |-- page_entry_ad.py
│   │   |-- page_file_download.py
│   │   |-- page_file_upload.py
│   │   |-- page_floating_menu.py
│   │   |-- page_forgot_password.py
│   │   |-- page_form_authentication_multi_user.py
│   │   |-- page_frame_inline.py
│   │   |-- page_frame_nested.py
│   │   |-- page_geolocation.py
│   │   |-- page_horizontal_slider.py
│   │   |-- page_hover.py
│   │   |-- page_infinite_scroll.py
│   │   |-- page_inputs.py
│   │   |-- page_jquery_ui.py
│   │   |-- page_js_alerts.py
│   │   |-- page_js_error.py
│   │   |-- page_key_press.py
│   │   |-- page_large_deep_dom.py
│   │   |-- page_new_window.py
│   │   |-- page_redirection.py
│   │   |-- page_secure_download.py
│   │   |-- page_shadow_dom.py
│   │   `-- page_shifting_content_menu.py
│   `-- utilities
│       |-- logger.py
│       `-- path_loader.py
|-- tests
│   |-- test_ab_test.py
│   |-- test_add_remove_elements.py
│   |-- test_basic_auth.py
│   |-- test_broken_images.py
│   |-- test_challenging_dom.py
│   |-- test_checkboxes.py
│   |-- test_context_menu.py
│   |-- test_digest_authentication.py
│   |-- test_disappearing_elements.py
│   |-- test_drag_drop.py
│   |-- test_dropdown.py
│   |-- test_dynamic_content.py
│   |-- test_dynamic_controls.py
│   |-- test_dynamic_elements.py
│   |-- test_entry_ad.py
│   |-- test_file_download.py
│   |-- test_file_upload.py
│   |-- test_floating_menu.py
│   |-- test_forgot_password.py
│   |-- test_form_authentication_multi_user.py
│   |-- test_frame_inline.py
│   |-- test_frame_nested.py
│   |-- test_geolocation.py
│   |-- test_horizontal_slider.py
│   |-- test_hover.py
│   |-- test_infinite_scroll.py
│   |-- test_inputs.py
│   |-- test_jquery_ui.py
│   |-- test_js_alerts.py
│   |-- test_js_error.py
│   |-- test_key_press.py
│   |-- test_large_deep_dom.py
│   |-- test_new_window.py
│   |-- test_redirection.py
│   |-- test_sample.py
│   |-- test_secure_download.py
│   |-- test_shadow_dom.py
│   `-- test_shifting_content_menu.py
|-- data
│   `-- login_users.json
|-- uploads
│   `-- uploadTestFile.txt
|-- downloads
|-- screenshots
|-- logs
`-- allure-results
```

## Technologies Used

- Python
- Playwright
- Pytest
- Pytest Playwright
- Pytest xdist
- Pytest rerunfailures
- Allure Pytest
- python-dotenv

## Setup

### 1. Install Python

#### Install Python according to your local setup.

#### Verify Python installation:

```bash
python --version
```

### 2. Create virtual environment

```bash
python -m venv .venv
```

### 3. Activate virtual environment

#### Windows:

```bash
.venv\Scripts\activate
```

#### Linux/macOS:

```bash
source .venv/bin/activate
```

### 4. Install project dependencies

```bash
pip install -r requirements.txt
```

### 5. Install Playwright browsers

```bash
playwright install
```

## Configuration

### Main configuration file:

```text
src/configurations/dev.json
```

### Important settings:

```json
{
  "browser": "chromium",
  "headless": true,
  "timeout": 30000,
  "base_url": "https://the-internet.herokuapp.com",
  "screenshot_dir": "screenshots/dev",
  "log_dir": "logs/dev",
  "downloads_dir": "downloads",
  "uploads_dir": "uploads",
  "data_dir": "data"
}
```

#### Environment selection is handled by:

```text
conftest.py
```

#### Default environment:

```text
dev
```

#### To run tests against a specific environment:

```bash
pytest --env=dev
```

The configuration loader expects environment files like:

```text
src/configurations/dev.json
src/configurations/stage.json
src/configurations/prod.json
```

## Run Tests

### Run all tests

```bash
pytest
```

### Run tests with a specific environment

```bash
pytest --env=dev
```

#### Examples:

```bash
pytest --env=prod
```

### Run tests with a specific browser

```bash
pytest --browser chromium
```

#### Supported browsers through Playwright:

```bash
pytest --browser chromium
pytest --browser firefox
pytest --browser webkit
```

### Run tests in headed mode

```bash
pytest --headed
```

### Run tests in parallel

```bash
pytest -n auto
```

#### Example with a fixed worker count:

```bash
pytest -n 4
```

### Run smoke tests

```bash
pytest -m smoke
```

### Run regression tests

```bash
pytest -m regression
```

### Run a specific test file

```bash
pytest tests/test_ab_test.py
```

### Run a specific test method

```bash
pytest tests/test_ab_test.py::test_ab_test_heading_compare_between_browsers
```

## Pytest Configuration

### Pytest configuration is defined in:

```text
pytest.ini
```

### Current default options:

```ini
addopts = -v --tb=short --strict-markers --alluredir=allure-results --reruns 1 --reruns-delay 2
testpaths = tests
python_files = test_*.py
python_classes = Test*
python_functions = test_*
log_cli = true
log_cli_level = DEBUG
log_cli_format = %(asctime)s | %(levelname)-8s | %(processName)s | %(threadName)s | %(name)s | %(message)s
log_cli_date_format = %Y-%m-%d %H:%M:%S
```

## Parallel Execution

### Parallel execution is provided by `pytest-xdist`.

### Run tests using all available CPU workers:

```bash
pytest -n auto
```

### Run tests with a specific number of workers:

```bash
pytest -n 4
```

## Test Markers

### Tests can be grouped using Pytest markers:

```python
@pytest.mark.smoke
@pytest.mark.regression
```

### Markers are registered in:

```text
pytest.ini
```

```ini
markers =
    smoke: smoke tests
    regression: regression tests
```

## Allure Report

### Generate Allure results

#### Allure results are generated automatically because `pytest.ini` includes:

```ini
--alluredir=allure-results
```

#### Run tests:

```bash
pytest
```

#### Or generate clean Allure results:

```bash
pytest --alluredir=allure-results --clean-alluredir
```

### Serve Allure report

#### Allure CLI must be installed and available in PATH to serve or generate reports.

```bash
allure serve allure-results
```

### Generate Allure report

```bash
allure generate allure-results -o allure-report --clean
```

#### Generated report location:

```text
allure-report
```

## Screenshots

### Screenshots are captured on test failure by the Pytest hook and fixture in:

```text
conftest.py
```

### Screenshot path is configured in:

```json
"screenshot_dir": "screenshots/dev"
```

### Screenshot output location:

```text
screenshots/dev
```

### Failure screenshots are also attached to the Allure report.

## Reports

### Allure results

```text
allure-results
```

### Screenshots

```text
screenshots/dev
```

### Logs

```text
logs/dev/main.log
```

## Retry Logic

### Retry behavior is configured in:

```text
pytest.ini
```

```ini
--reruns 1 --reruns-delay 2
```

This uses:

```text
pytest-rerunfailures
```

### To override retry count from the command line:

```bash
pytest --reruns 2 --reruns-delay 2
```

## Test Data

### JSON test data

```text
data/login_users.json
```

### Upload test file

```text
uploads/uploadTestFile.txt
```

### Downloads directory

```text
downloads
```

## Useful Pytest Commands

### Run all tests

```bash
pytest
```

### Run tests with verbose output

```bash
pytest -v
```

### Run a specific file

```bash
pytest tests/test_digest_authentication.py
```

### Run a specific test

```bash
pytest tests/test_login.py::test_digest_authentication
```

### Run smoke tests

```bash
pytest -m smoke
```

### Run regression tests

```bash
pytest -m regression
```

### Run tests in parallel

```bash
pytest -n auto
```

### Run tests and regenerate Allure results

```bash
pytest --alluredir=allure-results --clean-alluredir
```

## Notes

- Environment selection is controlled by `--env`.
- The default environment is `dev`.
- Environment configuration is loaded from `src/configurations/{env}.json`.
- Browser execution is handled by Playwright and Pytest Playwright.
- Default retry behavior is controlled from `pytest.ini`.
- Allure results are written to `allure-results`.
- Failed test screenshots are saved to `screenshots/dev`.
- The framework currently targets `https://the-internet.herokuapp.com`.
