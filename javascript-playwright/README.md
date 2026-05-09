# Playwright JavaScript Framework

A JavaScript Playwright automation framework using Page Object Model, environment-based configuration, reusable base classes, Playwright HTML reports, and Allure reporting.

## Features

- Playwright with JavaScript
- Playwright Test runner
- Page Object Model
- Base page and reusable test fixtures
- Browser execution using `@playwright/test`
- Configuration using environment-specific JSON files
- Environment switching using `TB_ENV`
- Allure reporting
- Screenshot and trace capture on test failure
- Retry logic
- Parallel execution
- Optional smoke and regression test scripts
- JSON test data support
- File upload and download support
- Logging using custom logger utilities

## Project Structure

```text
javascript-playwright
|-- README.md
|-- package.json
|-- playwright.config.js
|-- src
|   |-- configurations
|   |   |-- configuration-loader.js
|   |   `-- dev.json
|   |-- core
|   |   |-- base-page.js
|   |   `-- base-test.js
|   |-- pages
|   |   |-- page-ab-test.js
|   |   |-- page-add-remove-elements.js
|   |   |-- page-basic-auth.js
|   |   |-- page-broken-images.js
|   |   |-- page-challenging-dom.js
|   |   |-- page-checkboxes.js
|   |   |-- page-context-menu.js
|   |   |-- page-digest-authentication.js
|   |   |-- page-disappearing-elements.js
|   |   |-- page-drag-drop.js
|   |   |-- page-dropdown.js
|   |   |-- page-dynamic-content.js
|   |   |-- page-dynamic-controls.js
|   |   |-- page-dynamic-elements.js
|   |   |-- page-entry-ad.js
|   |   |-- page-file-download.js
|   |   |-- page-file-upload.js
|   |   |-- page-floating-menu.js
|   |   |-- page-forgot-password.js
|   |   |-- page-form-authentication-multi-user.js
|   |   |-- page-frame-inline.js
|   |   |-- page-frame-nested.js
|   |   |-- page-geolocation.js
|   |   |-- page-horizontal-slider.js
|   |   |-- page-hover.js
|   |   |-- page-infinite-scroll.js
|   |   |-- page-inputs.js
|   |   |-- page-jquery-ui.js
|   |   |-- page-js-alerts.js
|   |   |-- page-js-error.js
|   |   |-- page-key-press.js
|   |   |-- page-large-deep-dom.js
|   |   |-- page-new-window.js
|   |   |-- page-redirection.js
|   |   |-- page-secure-download.js
|   |   |-- page-shadow-dom.js
|   |   `-- page-shifting-content-menu.js
|   `-- utilities
|       |-- logger.js
|       `-- path-loader.js
|-- tests
|-- data
|   `-- login_users.json
|-- uploads
|   `-- uploadTestFile.txt
|-- downloads
|-- screenshots
|-- logs
`-- allure-results
    `-- dev
```

## Setup

Prerequisites:

- Node.js 18 or newer
- npm
- Internet access for installing Playwright browsers and running tests against public demo pages

```bash
npm install
npx playwright install
```

On Windows PowerShell, use `npx.cmd` if `npx` is blocked by execution policy:

```powershell
npx.cmd playwright install
```

## Run Tests

```bash
npm test
npx playwright test tests/test-ab-test.spec.js
npx playwright test --project=chromium
npm run test:headed
```

Optional tag-based scripts are available. Add `@smoke` or `@regression` to test titles before using them:

```bash
npm run test:smoke
npm run test:regression
```

Run with a specific environment:

```bash
TB_ENV=dev npm test
```

Windows PowerShell:

```powershell
$env:TB_ENV="dev"
npm test
```

The default environment is `dev`, loaded from `src/configurations/dev.json`.

## Reports

Playwright HTML report:

```bash
npm run report
```

Allure results are generated after test execution in `allure-results/dev`.

Serve the Allure report:

```bash
npx allure serve allure-results/dev
```

Windows PowerShell:

```powershell
npx.cmd allure serve allure-results/dev
```

Generate a static Allure report:

```bash
npx allure generate allure-results/dev -o reports/allure-dev
```

Windows PowerShell:

```powershell
npx.cmd allure generate allure-results/dev -o reports/allure-dev
```

## Configuration

Environment-specific settings live in `src/configurations/*.json`.

Important `dev.json` values:

- `base_url`: application under test
- `browser`: Playwright browser project
- `headless`: headless or headed execution
- `timeout`: test and action timeout
- `reports_dir`: Playwright HTML report directory
- `allure_results_dir`: Allure raw results directory
- `screenshot_dir`: failure screenshot directory

## Notes

- Generated folders such as `test-results`, `reports`, `allure-results`, `screenshots`, `logs`, and `downloads` should not be committed.
- Some tests use public demo pages from `https://the-internet.herokuapp.com`, so they require internet access.

## Git Ignore Verification

Verified. No additional `.gitignore` change was needed for generated runtime folders.

These generated folders are already ignored by the parent `.gitignore`:

```text
javascript-playwright/test-results/
javascript-playwright/reports/
javascript-playwright/allure-results/
javascript-playwright/logs/
javascript-playwright/screenshots/
javascript-playwright/downloads/
```

Git confirms them as ignored with `!!`, so they will not be uploaded to GitHub unless force-added.

Verification command:

```bash
git status --short --ignored javascript-playwright
```
