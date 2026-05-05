# Enterprise Playwright Python Automation Framework

Enterprise-style UI automation framework built with Playwright, Python, pytest, and Allure for testing a realistic HRM web application.

Target application:

```text
https://opensource-demo.orangehrmlive.com/
```

## Project Overview

This framework automates business workflows in the OrangeHRM demo application using a maintainable Page Object Model structure.

Covered modules:

- Login
- Dashboard
- Admin user management
- PIM employee management

## Tech Stack

- Python
- Playwright
- pytest
- pytest-playwright
- pytest-xdist
- Allure Reporting
- PyYAML
- python-dotenv

## Framework Features

- Page Object Model
- Data-driven testing using JSON
- Config-driven browser and URL setup
- Environment-based credential management
- pytest markers for smoke, regression, and module-based execution
- Parallel execution with pytest-xdist
- Screenshots on failure
- Playwright trace recording
- Allure report integration
- Reusable utility layer for config, data, logging, and screenshots

## Project Structure

```text
enterprise-playwright-python-framework/
│
├── config/
│   └── config.yaml
│
├── pages/
│   ├── login_page.py
│   ├── dashboard_page.py
│   ├── admin_page.py
│   └── pim_page.py
│
├── tests/
│   ├── test_login.py
│   ├── test_admin_users.py
│   └── test_employee_management.py
│
├── test_data/
│   ├── users.json
│   └── employees.json
│
├── utils/
│   ├── config_reader.py
│   ├── data_reader.py
│   ├── logger.py
│   └── screenshot_util.py
│
├── reports/
│   ├── allure-results/
│   ├── screenshots/
│   ├── traces/
│   ├── videos/
│   └── logs/
│
├── conftest.py
├── pytest.ini
├── requirements.txt
├── .env
├── .gitignore
└── README.md
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

Or install Chromium only:

```powershell
playwright install chromium
```

## Environment Variables

Create a `.env` file in the project root:

```env
ORANGEHRM_USERNAME=Admin
ORANGEHRM_PASSWORD=admin123
```

## Configuration

Main configuration is stored in:

```text
config/config.yaml
```

Example:

```yaml
app:
  base_url: "https://opensource-demo.orangehrmlive.com/"

browser:
  name: "chromium"
  headless: true
  viewport:
    width: 1366
    height: 768
  timeout: 10000

reports:
  video_dir: "reports/videos"
  trace_dir: "reports/traces"
  screenshot_dir: "reports/screenshots"
```

## Running Tests

Run all tests:

```powershell
.\.venv\Scripts\python.exe -m pytest
```

Run login tests:

```powershell
.\.venv\Scripts\python.exe -m pytest tests/test_login.py
```

Run Admin tests:

```powershell
.\.venv\Scripts\python.exe -m pytest tests/test_admin_users.py
```

Run PIM tests:

```powershell
.\.venv\Scripts\python.exe -m pytest tests/test_employee_management.py
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

Run login tests by marker:

```powershell
.\.venv\Scripts\python.exe -m pytest -m login
```

Run Admin tests by marker:

```powershell
.\.venv\Scripts\python.exe -m pytest -m admin
```

Run PIM tests by marker:

```powershell
.\.venv\Scripts\python.exe -m pytest -m pim
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

Allure CLI must be installed separately.

## Test Coverage

### Login Module

- Valid login
- Invalid login
- Required field validation
- Logout

### Admin Module

- Search user by username
- Search users by role
- Reset search filters
- Validate table results

### PIM Module

- Add employee
- Search employee
- Reset employee search
- Validate employee details page

## Notes

OrangeHRM is a public shared demo environment. Some workflows, especially employee creation and search, can be affected by changing demo data or application slowness.

For stable execution, login and Admin search tests are better candidates for smoke and parallel execution. PIM tests should be treated as broader regression coverage.

## Portfolio Value

This project demonstrates:

- scalable automation framework design
- business workflow automation
- Page Object Model implementation
- reusable configuration and test data handling
- pytest-based test organization
- reporting and debugging support
- parallel test execution readiness
- professional QA automation project structure
