# Playwright Python API Automation Framework

API automation framework built with Playwright, Python, pytest, and Allure for testing the ReqRes API.

Target API:

```text
https://reqres.in
```

## Project Overview

This framework automates REST API validation for ReqRes user management endpoints using a maintainable service-layer structure.

Covered modules:

- Fetch all users
- Fetch single user
- Create user
- Update user
- Delete user

## Tech Stack

- Python
- Playwright API testing
- pytest
- pytest-playwright
- pytest-xdist
- pytest-html
- Allure Reporting
- python-dotenv

## Framework Features

- API service layer for reusable endpoint methods
- Data-driven configuration using JSON
- Config-driven base URL, API key, logging, reports, and folders
- Environment-based configuration selection using `--env`
- pytest markers for smoke, regression, and module-based execution
- Parallel execution with pytest-xdist
- Allure report integration
- Request and response attachments in Allure reports
- Failure traceback and execution log attachments in Allure reports
- Screenshot attachment support for future UI tests using Playwright `page`
- Reusable utility layer for config and logging

## Project Structure

```text
python_playwright_api_framework/
|
|-- configurations/
|   |-- config_reader.py
|   `-- dev.json
|
|-- services/
|   `-- api_services.py
|
|-- tests/
|   `-- test_users.py
|
|-- utilities/
|   `-- logger.py
|
|-- logs/
|   `-- dev/
|
|-- reports/
|   `-- allure-results/
|
|-- screenshots/
|   `-- dev/
|
|-- conftest.py
|-- pytest.ini
|-- requirements.txt
`-- README.md
```

## Setup Instructions

Create and activate a virtual environment:

```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
```

Install dependencies:

```powershell
pip install -r requirements.txt
```

Install Playwright browsers:

```powershell
playwright install
```

For API testing, browser installation is usually not required, but it is useful if UI tests are added later.

## Environment Variables

This project reads configuration from JSON files under:

```text
configurations/
```

The active environment is selected by pytest option:

```powershell
.\.venv\Scripts\python.exe -m pytest --env=dev
```

If no environment is provided, `dev` is used by default.

## Configuration

Main development configuration is stored in:

```text
configurations/dev.json
```

Example:

```json
{
    "base_url": "https://reqres.in",
    "api_key": "your_api_key",
    "log_level": "DEBUG",
    "console_log_level": "DEBUG",
    "log_dir": "logs/dev",
    "screenshot_dir": "screenshots/dev",
    "allure_results_dir": "allure-results/dev",
    "reports_dir": "reports",
    "email": "eve.holt@reqres.in",
    "password": "pistol",
    "name": "Updated Name",
    "job": "Updated Job"
}
```

## Running Tests

Run all tests:

```powershell
.\.venv\Scripts\python.exe -m pytest
```

Run all tests for the dev environment:

```powershell
.\.venv\Scripts\python.exe -m pytest --env=dev
```

Run user API tests:

```powershell
.\.venv\Scripts\python.exe -m pytest tests/test_users.py
```

Run with short traceback and verbose output:

```powershell
.\.venv\Scripts\python.exe -m pytest -v --tb=short
```

## Running by Marker

Run smoke tests:

```powershell
.\.venv\Scripts\python.exe -m pytest -m smoke
```

Run regression tests:

```powershell
.\.venv\Scripts\python.exe -m pytest -m regression
```

Run user management API tests:

```powershell
.\.venv\Scripts\python.exe -m pytest -m users
```

## Parallel Execution

Run tests in parallel:

```powershell
.\.venv\Scripts\python.exe -m pytest -n 2
```

Run smoke tests in parallel:

```powershell
.\.venv\Scripts\python.exe -m pytest -m smoke -n 2
```

## Allure Reporting

Run tests and generate Allure result files:

```powershell
.\.venv\Scripts\python.exe -m pytest --alluredir=reports/allure-results
```

Serve the Allure report:

```powershell
allure serve reports/allure-results
```

Generate a static Allure report:

```powershell
allure generate reports/allure-results -o reports/allure-report --clean
```

Allure CLI must be installed separately.

Allure attachments include:

- API request method, URL, headers, and payload
- API response status, headers, and body
- Failure traceback
- Execution log file
- Failure screenshot when a Playwright `page` fixture is used

## Test Coverage

### Users Module

- Fetch all users
- Validate pagination fields
- Validate user schema fields
- Create user
- Validate created user response
- Fetch single user
- Update user
- Validate updated user response
- Delete user
- Validate delete response status

## Notes

ReqRes is a public API used for testing and demonstration. Some behavior is simulated by the service, so create, update, and delete operations validate response contracts rather than persistent backend state.

For stable execution, user API tests are suitable for smoke execution and parallel runs. If more endpoints are added, keep endpoint calls inside the service layer and assertions inside the test layer.

## Portfolio Value

This project demonstrates:

- scalable API automation framework design
- Playwright API testing with pytest
- reusable API service-layer implementation
- JSON-based configuration handling
- pytest-based test organization
- Allure reporting with request and response evidence
- logging and failure debugging support
- parallel test execution readiness
- professional QA automation project structure
