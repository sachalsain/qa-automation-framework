# Playwright JavaScript API Automation Framework

API automation framework built with Playwright, JavaScript, Playwright Test, and Allure for testing the ReqRes API.

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

- JavaScript
- Playwright API testing
- Playwright Test
- Allure Reporting
- cross-env

## Framework Features

- API service layer for reusable endpoint methods
- Data-driven configuration using JSON
- Config-driven base URL, API key, logging, reports, and folders
- Environment-based configuration selection using `TB_ENV`
- Tag-based execution for smoke, regression, and module-based runs
- Parallel execution with Playwright workers
- Allure report integration
- Request and response attachments in test reports
- Failure traceback and execution log attachments in test reports
- Screenshot attachment support for future UI tests using Playwright `page`
- Reusable utility layer for config and logging

## Project Structure

```text
javascript_playwright_api_framework/
|
|-- configurations/
|   |-- config_reader.js
|   `-- dev.json
|
|-- services/
|   `-- api_services.js
|
|-- tests/
|   `-- test_users.spec.js
|
|-- utilities/
|   |-- logger.js
|   `-- path_loader.js
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
|-- fixtures.js
|-- playwright.config.js
|-- package.json
`-- README.md
```

## Setup Instructions

Prerequisites:

- Node.js 20 or newer
- npm
- Allure CLI, only if you want to serve or generate Allure reports

Install dependencies:

```powershell
npm install
```

Install Playwright browsers:

```powershell
npx playwright install
```

For API testing, browser installation is usually not required, but it is useful if UI tests are added later.

## Environment Variables

This project reads configuration from JSON files under:

```text
configurations/
```

The active environment is selected by the `TB_ENV` environment variable:

```powershell
$env:TB_ENV = "dev"
npm test
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
npm test
```

If PowerShell blocks `npm.ps1` because script execution is disabled, use:

```powershell
npm.cmd test
```

Run all tests for the dev environment:

```powershell
npm run test:dev
```

Run user API tests:

```powershell
npx playwright test tests/test_users.spec.js
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

Run user management API tests:

```powershell
npm run test:users
```

## Parallel Execution

Run tests in parallel:

```powershell
npm run test:parallel
```

Run smoke tests in parallel:

```powershell
npx playwright test --grep "@smoke" --workers=2
```

## Allure Reporting

Run tests and generate Allure result files:

```powershell
npm test
```

Serve the Allure report:

```powershell
npm run allure:serve
```

Generate a static Allure report:

```powershell
npm run allure:generate
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

## GitHub Notes

Commit source files such as `package.json`, `package-lock.json`, `playwright.config.js`, `fixtures.js`, `configurations/`, `services/`, `tests/`, `utilities/`, and `README.md`.

Do not commit generated runtime output such as `node_modules/`, `test-results/`, `reports/`, `logs/`, `screenshots/`, or `allure-results/`. These folders are already covered by the parent `.gitignore`.

## Portfolio Value

This project demonstrates:

- scalable API automation framework design
- Playwright API testing with JavaScript
- reusable API service-layer implementation
- JSON-based configuration handling
- Playwright Test-based test organization
- Allure reporting with request and response evidence
- logging and failure debugging support
- parallel test execution readiness
- professional QA automation project structure
