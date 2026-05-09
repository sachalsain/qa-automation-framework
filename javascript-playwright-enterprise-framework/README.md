# Enterprise Playwright JavaScript Automation Framework

Enterprise-style UI automation framework built with Playwright Test, JavaScript, and Allure for testing a realistic HRM web application.

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

- JavaScript
- Playwright Test
- Allure Reporting
- dotenv
- YAML

## Framework Features

- Page Object Model
- Data-driven testing using JSON
- Config-driven browser and URL setup
- Environment-based credential management
- Playwright tags for smoke, regression, and module-based execution
- Parallel execution with Playwright workers
- Screenshots on failure
- Playwright trace recording
- Allure report integration
- Reusable utility layer for config, data, logging, and screenshots

## Project Structure

```text
javascript-playwright-enterprise-framework/
|
|-- config/
|   `-- config.yaml
|
|-- pages/
|   |-- login_page.js
|   |-- dashboard_page.js
|   |-- admin_page.js
|   `-- pim_page.js
|
|-- tests/
|   |-- test_login.spec.js
|   |-- test_admin_users.spec.js
|   `-- test_employee_management.spec.js
|
|-- test_data/
|   |-- users.json
|   `-- employees.json
|
|-- utils/
|   |-- config_reader.js
|   |-- data_reader.js
|   |-- logger.js
|   `-- screenshot_util.js
|
|-- reports/
|   |-- allure-results/
|   |-- screenshots/
|   |-- traces/
|   |-- videos/
|   `-- logs/
|
|-- fixtures.js
|-- playwright.config.js
|-- package.json
|-- .env.example
|-- .gitignore
`-- README.md
```

## Setup Instructions

Install dependencies:

```powershell
npm install
```

Install Playwright browsers:

```powershell
npx playwright install
```

Or install Chromium only:

```powershell
npx playwright install chromium
```

## Environment Variables

Create a `.env` file in the project root:

```env
ORANGEHRM_USERNAME=Admin
ORANGEHRM_PASSWORD=admin123
```

The current test data uses the public demo credentials directly to match the Python framework.

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
  workers: 2
  locale: "en-US"
  viewport:
    width: 1366
    height: 768
  timeout: 30000

reports:
  video_dir: "reports/videos"
  trace_dir: "reports/traces"
  screenshot_dir: "reports/screenshots"
```

## Running Tests

Run all tests:

```powershell
npm test
```

Run login tests:

```powershell
npx playwright test tests/test_login.spec.js
```

Run Admin tests:

```powershell
npx playwright test tests/test_admin_users.spec.js
```

Run PIM tests:

```powershell
npx playwright test tests/test_employee_management.spec.js
```

## Running by Tag

Run smoke tests:

```powershell
npm run test:smoke
```

Run regression tests:

```powershell
npm run test:regression
```

Run login tests by tag:

```powershell
npm run test:login
```

Run Admin tests by tag:

```powershell
npm run test:admin
```

Run PIM tests by tag:

```powershell
npm run test:pim
```

## Parallel Execution

Run tests in parallel:

```powershell
npx playwright test --workers=2
```

Run smoke tests in parallel:

```powershell
npx playwright test --grep @smoke --workers=2
```

## Allure Reporting

Run tests and generate Allure result files:

```powershell
npx playwright test
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
- Playwright Test organization
- reporting and debugging support
- parallel test execution readiness
- professional QA automation project structure
