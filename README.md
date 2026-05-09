# QA Automation Framework Portfolio

A professional QA automation portfolio demonstrating reusable UI and API test automation frameworks built with Playwright, Selenium, Java, Python, TestNG, Pytest, Maven, and Allure reporting.

This repository is designed to show how automated testing can help teams reduce regression bugs, validate critical user flows, and improve release confidence through maintainable test automation systems.

## Problem This Solves

Modern web applications and APIs change frequently. Without automated regression testing, teams risk shipping broken login flows, unstable UI behavior, failed form submissions, broken navigation, API contract regressions, and other production issues.

This portfolio demonstrates automation frameworks that help teams:

- Validate critical user journeys before release
- Validate API contracts and response behavior
- Reduce repetitive manual regression testing
- Detect failures earlier in the development cycle
- Capture screenshots and reports for faster debugging
- Run smoke and regression suites consistently
- Build a maintainable foundation for future test coverage

## Screenshots

### QA Automation Test Report

![QA Automation Test Report](docs/images/qa-automation-test-report.png)

A generated sample report view showing passed, failed, skipped, duration, and test case details.

### Playwright Browser Automation

![Playwright Browser Automation](docs/images/java-playwright-browser-automation.png)

A generated sample showing a Herokuapp A/B Testing page beside a passing Java Playwright test run.

## Frameworks Included

| Framework | Language | Tooling | Best For |
|-----------|----------|---------|----------|
| Java Playwright | Java | Playwright, TestNG, Maven, Allure | Enterprise-style Playwright UI automation |
| JavaScript Playwright | JavaScript | Playwright Test, npm, Allure | Modern Playwright UI automation with JavaScript |
| Python Playwright | Python | Playwright, Pytest, Allure | Fast and lightweight Playwright automation |
| Python Playwright Enterprise | Python | Playwright, Pytest, PyYAML, Allure | Enterprise-style OrangeHRM business workflow automation |
| Python Playwright API | Python | Playwright API, Pytest, Allure | REST API automation with request and response evidence |
| JavaScript Playwright API | JavaScript | Playwright API, Playwright Test, npm, Allure | REST API automation with JavaScript service-layer design |
| Selenium Framework | Java | Selenium WebDriver, TestNG, Maven, Allure | Selenium WebDriver and legacy automation support |

## Repository Structure

```text
qa-automation-framework
|-- java-playwright
|   |-- README.md
|   |-- pom.xml
|   |-- testng.xml
|   `-- src
|-- javascript-playwright
|   |-- README.md
|   |-- package.json
|   |-- playwright.config.js
|   |-- src
|   |-- tests
|   |-- data
|   `-- uploads
|-- python-playwright
|   |-- README.md
|   |-- requirements.txt
|   |-- pytest.ini
|   |-- conftest.py
|   `-- src
|-- python-playwright-enterprise-framework
|   |-- README.md
|   |-- requirements.txt
|   |-- pytest.ini
|   |-- conftest.py
|   |-- config
|   |-- pages
|   |-- tests
|   |-- test_data
|   `-- utils
|-- python_playwright_api_framework
|   |-- README.md
|   |-- requirements.txt
|   |-- pytest.ini
|   |-- conftest.py
|   |-- configurations
|   |-- services
|   |-- tests
|   `-- utilities
|-- javascript_playwright_api_framework
|   |-- README.md
|   |-- package.json
|   |-- playwright.config.js
|   |-- fixtures.js
|   |-- configurations
|   |-- services
|   |-- tests
|   `-- utilities
|-- java-selenium
|   |-- README.md
|   |-- pom.xml
|   |-- testng.xml
|   `-- src
`-- README.md
```

## Key Features

- Page Object Model structure
- Reusable base test setup
- Config-based execution
- Browser selection from configuration
- Parallel test execution
- Retry logic for failed tests
- Screenshot capture on test failure
- Allure reporting
- Smoke and regression test grouping
- JSON test data support
- Excel and CSV utility support
- Logging support
- File upload and download test coverage
- OrangeHRM business workflow automation
- REST API automation with Playwright request contexts
- API request and response evidence in Allure reports
- CI/CD-ready project structure

## Project 1: Java Playwright Framework

Location:

```text
java-playwright
```

This framework demonstrates browser automation using Playwright with Java, TestNG, Maven, and Allure reporting.

Main highlights:

- Playwright Java
- TestNG test execution
- Maven build management
- Page Object Model
- Browser factory
- Configurable browser execution
- Parallel execution through TestNG
- Retry logic using TestNG `IRetryAnalyzer`
- Screenshot capture on failure
- Allure reporting
- JSON, Excel, and CSV utility support
- Logging with SLF4J and Logback

Run tests:

```bash
cd java-playwright
mvn test
```

Generate Allure report:

```bash
mvn allure:serve
```

More details:

```text
java-playwright/README.md
```

## Project 2: JavaScript Playwright Framework

Location:

```text
javascript-playwright
```

This framework demonstrates browser automation using Playwright with JavaScript, Playwright Test, npm, and Allure reporting.

Main highlights:

- Playwright JavaScript
- Playwright Test execution
- npm dependency management
- Page Object Model
- Environment-based JSON configuration
- Reusable fixtures for authenticated, cross-browser, and geolocation scenarios
- Parallel execution through Playwright Test
- Retry logic through Playwright configuration
- Screenshot and trace capture on failure
- Playwright HTML reports
- Allure reporting
- JSON test data support
- File upload and download support
- Custom logging utilities

Run tests:

```bash
cd javascript-playwright
npm test
```

List discovered tests:

```bash
npm test -- --list
```

Generate Playwright report:

```bash
npm run report
```

More details:

```text
javascript-playwright/README.md
```

## Project 3: Python Playwright Framework

Location:

```text
python-playwright
```

This framework demonstrates browser automation using Playwright with Python, Pytest, and Allure reporting.

Main highlights:

- Playwright Python
- Pytest test execution
- Pytest Playwright integration
- Page Object Model
- Environment-based configuration
- Parallel execution with `pytest-xdist`
- Retry logic with `pytest-rerunfailures`
- Screenshot capture on failure
- Allure reporting
- JSON test data support
- File upload and download support
- Custom logging utilities

Run tests:

```bash
cd python-playwright
pytest
```

Run tests in parallel:

```bash
pytest -n auto
```

Generate Allure report:

```bash
allure serve allure-results
```

More details:

```text
python-playwright/README.md
```

## Project 4: Python Playwright Enterprise Framework

Location:

```text
python-playwright-enterprise-framework
```

This framework demonstrates enterprise-style browser automation using Playwright with Python, Pytest, PyYAML configuration, environment-based credentials, and Allure reporting.

The framework targets the OrangeHRM demo application:

```text
https://opensource-demo.orangehrmlive.com/
```

Main highlights:

- Playwright Python
- Pytest test execution
- Page Object Model
- OrangeHRM business workflow automation
- Login, Dashboard, Admin, and PIM module coverage
- Data-driven testing using JSON
- YAML-based configuration
- Environment-based credential management with `.env`
- Pytest markers for smoke, regression, login, admin, and PIM tests
- Parallel execution with `pytest-xdist`
- Screenshot capture on failure
- Playwright trace recording
- Allure reporting
- Reusable utility layer for config, data, logging, and screenshots

Run tests:

```bash
cd python-playwright-enterprise-framework
pytest
```

Run smoke tests:

```bash
pytest -m smoke
```

Run tests in parallel:

```bash
pytest -n 2
```

Generate Allure report:

```bash
pytest --alluredir=reports/allure-results
allure serve reports/allure-results
```

More details:

```text
python-playwright-enterprise-framework/README.md
```

## Project 5: Python Playwright API Framework

Location:

```text
python_playwright_api_framework
```

This framework demonstrates REST API automation using Playwright API testing with Python, Pytest, JSON configuration, and Allure reporting.

The framework targets the ReqRes demo API:

```text
https://reqres.in
```

Main highlights:

- Playwright API testing
- Pytest test execution
- Service-layer API client structure
- ReqRes user management endpoint coverage
- JSON-based environment configuration
- Environment selection with `--env`
- Pytest markers for smoke, regression, and users tests
- Parallel execution with `pytest-xdist`
- Allure reporting
- Request and response attachments in Allure reports
- Reusable utility layer for config and logging

Run tests:

```bash
cd python_playwright_api_framework
pytest
```

Run smoke tests:

```bash
pytest -m smoke
```

Run tests in parallel:

```bash
pytest -n 2
```

Generate Allure report:

```bash
pytest --alluredir=reports/allure-results
allure serve reports/allure-results
```

More details:

```text
python_playwright_api_framework/README.md
```

## Project 6: JavaScript Playwright API Framework

Location:

```text
javascript_playwright_api_framework
```

This framework demonstrates REST API automation using Playwright API testing with JavaScript, Playwright Test, JSON configuration, and Allure reporting.

The framework targets the ReqRes demo API:

```text
https://reqres.in
```

Main highlights:

- Playwright API testing with JavaScript
- Playwright Test execution
- Service-layer API client structure
- ReqRes user management endpoint coverage
- JSON-based environment configuration
- Environment selection with `TB_ENV`
- Tag-based execution for smoke and users tests
- Parallel execution with Playwright workers
- Playwright HTML reporting
- Allure reporting
- Request and response attachments in reports
- Reusable utility layer for config and logging

Run tests:

```bash
cd javascript_playwright_api_framework
npm test
```

Run smoke tests:

```bash
npm run test:smoke
```

Run tests in parallel:

```bash
npm run test:parallel
```

Generate Allure report:

```bash
npm test
npm run allure:serve
```

More details:

```text
javascript_playwright_api_framework/README.md
```

## Project 7: Selenium Java Framework

Location:

```text
java-selenium
```

This framework demonstrates browser automation using Selenium WebDriver with Java, TestNG, Maven, and Allure reporting.

Main highlights:

- Selenium WebDriver
- TestNG test execution
- Maven build management
- Page Object Model
- Driver factory
- Configurable browser execution
- Parallel execution through TestNG
- Retry logic using TestNG `IRetryAnalyzer`
- Screenshot capture on failure
- Allure reporting
- JSON, Excel, and CSV utility support
- Logging with SLF4J and Logback

Run tests:

```bash
cd java-selenium
mvn test
```

Generate Allure report:

```bash
mvn allure:serve
```

More details:

```text
java-selenium/README.md
```

## Use Cases

These frameworks are suitable for:

- Startups needing a fast automation setup
- Teams starting UI automation from scratch
- Projects requiring smoke and regression test coverage
- QA teams moving from manual regression to automation
- Selenium teams planning to adopt Playwright
- Freelance QA automation project demonstrations
- CI/CD pipeline test execution
- API test automation and service contract validation
- Proof-of-concept automation frameworks

## Test Coverage Examples

The frameworks include examples for common web automation scenarios such as:

- Login and authentication
- Basic authentication
- Digest authentication
- Checkbox validation
- Dropdown selection
- File upload
- File download
- Broken image validation
- JavaScript alerts
- Frames
- New windows
- Dynamic controls
- Dynamic content
- Drag and drop
- Hover actions
- Key presses
- Infinite scroll
- Geolocation
- Shadow DOM
- Redirects
- OrangeHRM login workflows
- OrangeHRM Admin user search
- OrangeHRM PIM employee management
- ReqRes user API list, create, update, and delete flows
- API response status, schema, and contract validation

## Reporting

The frameworks support Allure reporting to provide clear test execution results.

Reports can include:

- Passed test cases
- Failed test cases
- Skipped test cases
- API request and response attachments
- Failure screenshots
- Error logs
- Test execution timeline
- Test suite history
- Environment details

Example report command for Java-based frameworks:

```bash
mvn allure:serve
```

Example report command for the standard Python framework:

```bash
allure serve allure-results
```

Example report command for the enterprise Python framework:

```bash
pytest --alluredir=reports/allure-results
allure serve reports/allure-results
```

Example report command for the Python API framework:

```bash
cd python_playwright_api_framework
pytest --alluredir=reports/allure-results
allure serve reports/allure-results
```

Example report command for the JavaScript API framework:

```bash
cd javascript_playwright_api_framework
npm test
npm run allure:serve
```

## Screenshots

Failure screenshots are captured automatically in UI frameworks when tests fail. The API framework also has a screenshot folder configured for future Playwright UI tests that use a `page` fixture.

Typical screenshot locations:

```text
java-playwright/target/screenshots
javascript-playwright/screenshots/dev
python-playwright/screenshots/dev
python-playwright-enterprise-framework/reports/screenshots
python_playwright_api_framework/screenshots/dev
javascript_playwright_api_framework/screenshots/dev
java-selenium/target/screenshots
```

Screenshots help with:

- Faster failure debugging
- Visual proof of application behavior
- Better reporting inside Allure
- Easier communication with developers and stakeholders

## Configuration

Each framework supports configuration-driven execution.

Common configurable items include:

- Base URL
- Browser
- Headless mode
- Timeout values
- Retry count
- Screenshot path
- Test data path
- API key or service credentials when required
- Environment settings

Configuration files:

```text
java-playwright/src/test/resources/configuration.properties
javascript-playwright/src/configurations/dev.json
python-playwright/src/configurations/dev.json
python-playwright-enterprise-framework/config/config.yaml
python_playwright_api_framework/configurations/dev.json
javascript_playwright_api_framework/configurations/dev.json
java-selenium/src/main/resources/configuration.properties
```

## Parallel Execution

Parallel execution is supported to reduce total test execution time.

Java Playwright and Selenium use TestNG suite configuration:

```xml
<suite name="QA-Automation-Suite" parallel="methods" thread-count="2">
```

Python Playwright frameworks use `pytest-xdist`:

```bash
pytest -n auto
```

The JavaScript Playwright framework uses Playwright Test workers:

```bash
cd javascript-playwright
npm test
```

The enterprise Python framework can also run with an explicit worker count:

```bash
pytest -n 2
```

The Python API framework also supports `pytest-xdist`:

```bash
cd python_playwright_api_framework
pytest -n 2
```

The JavaScript API framework uses Playwright Test workers:

```bash
cd javascript_playwright_api_framework
npm run test:parallel
```

## Retry Logic

Retry logic is included to reduce the impact of temporary test instability.

Java-based frameworks use TestNG retry analyzers:

```text
RetryAnalyzer.java
```

The standard Python Playwright framework uses `pytest-rerunfailures`:

```ini
--reruns 1 --reruns-delay 2
```

The JavaScript Playwright framework uses Playwright Test retries configured in:

```text
javascript-playwright/playwright.config.js
```

## Generated Files And Git Ignore

Runtime output is intentionally excluded from GitHub. The parent `.gitignore` already ignores generated folders used by the JavaScript Playwright project:

```text
javascript-playwright/test-results/
javascript-playwright/reports/
javascript-playwright/allure-results/
javascript-playwright/logs/
javascript-playwright/screenshots/
javascript-playwright/downloads/
```

Verified with:

```bash
git status --short --ignored javascript-playwright
```

Git reports these folders with `!!`, so they will not be uploaded to GitHub unless force-added.

## CI/CD Readiness

The frameworks are structured to be integrated with CI/CD tools such as:

- GitHub Actions
- Jenkins
- GitLab CI
- Azure DevOps
- Bitbucket Pipelines

Typical CI/CD workflow:

```text
1. Checkout code
2. Install dependencies
3. Install browsers if using Playwright
4. Run smoke or regression tests
5. Generate reports
6. Upload test artifacts
```

## Tech Stack

|		  Area  		|					Tools						|
|-----------------------|-----------------------------------------------|
|	Browser Automation	|	Playwright, Selenium WebDriver				|
|	API Automation		|	Playwright API testing						|
|	Languages			|	Java, JavaScript, Python					|
|	Test Frameworks		|	TestNG, Playwright Test, Pytest				|
|	Build Tools			|	Maven, npm, pip								|
|	Reporting			|	Allure										|
|	Data Handling		|	JSON, Excel, CSV, YAML						|
|	Logging				|	SLF4J, Logback, Python logging				|
|	Parallel Execution	|	TestNG, pytest-xdist						|
|	Retry Logic			|	TestNG IRetryAnalyzer, pytest-rerunfailures |

## Why This Portfolio Matters

This repository is not just a collection of test scripts. It demonstrates how to build maintainable automation systems.

The focus is on:

- Clean project structure
- Reusable page objects
- Centralized configuration
- Scalable test execution
- Clear reports
- Failure evidence
- Test data management
- Maintainability

## Service Offer

I help teams set up scalable UI and API QA automation frameworks using Playwright, Selenium, Java, and Python.

Services include:

- UI automation framework setup
- API automation framework setup
- Smoke and regression test suite creation
- Selenium to Playwright migration
- Flaky test fixes
- Allure report integration
- CI/CD test execution setup
- Test data and configuration management
- Automation framework documentation

## Suggested Automation Packages

### Pilot Automation Setup

Best for validating automation value quickly.

Includes:

- 2 to 3 critical UI test cases
- Basic Page Object Model structure
- Screenshot on failure
- Basic reporting

### Core Automation Framework

Best for startups or small teams needing a reusable automation foundation.

Includes:

- 5 to 10 UI test cases
- Framework setup
- Page Object Model
- Config-based execution
- Parallel execution
- Retry logic
- Allure reporting
- CI/CD-ready structure

### Advanced QA Automation System

Best for teams needing a complete automation solution.

Includes:

- Full UI automation framework
- Smoke and regression suites
- API automation support if required
- Reporting and screenshots
- CI/CD integration
- Test data management
- Documentation

## How To Review This Repository

Recommended review order:

```text
1. Start with this main README
2. Open java-playwright/README.md
3. Open javascript-playwright/README.md
4. Open python-playwright/README.md
5. Open python-playwright-enterprise-framework/README.md
6. Open python_playwright_api_framework/README.md
7. Open javascript_playwright_api_framework/README.md
8. Open java-selenium/README.md
9. Review framework structure and test examples
10. Run one framework locally
11. Generate Allure report
```

## Contact

Available for QA automation projects involving:

- Playwright automation
- Selenium automation
- API automation
- Test automation framework setup
- CI/CD test integration
- Regression suite creation
- Test reporting improvement
- Flaky test stabilization

https://www.tahreems.com
