# Playwright Java Framework

UI automation framework for [The Internet](https://the-internet.herokuapp.com) using Playwright, Java, Maven, TestNG, and Allure reporting.

## Features

- Playwright with Java
- TestNG test framework
- Maven build management
- Page Object Model
- Base test setup and teardown
- Browser factory using Playwright
- Configuration using `configuration.properties`
- Allure reporting
- Screenshot capture on test failure
- Retry logic with TestNG `IRetryAnalyzer`
- Parallel execution using TestNG suite configuration
- Test groups for smoke and regression
- JSON test data support
- Excel test data support
- CSV utility support
- Logging with SLF4J and Logback

## Project Structure

```text
java-playwright
|-- pom.xml
|-- testng.xml
|-- src
|   |-- main
|   |   |-- java
|   |   `-- resources
|   |       `-- logback.xml
|   `-- test
|       |-- java
|       |   `-- com.techbytes.qa
|       |       |-- core
|       |       |   |-- BasePage.java
|       |       |   |-- BaseTest.java
|       |       |   |-- BrowserType.java
|       |       |   |-- ConfigurationLoader.java
|       |       |   `-- PlaywrightFactory.java
|       |       |-- listeners
|       |       |   |-- RetryAnalyzer.java
|       |       |   `-- TestListener.java
|       |       |-- pages
|       |       |   |-- ABTestingPage.java
|       |       |   |-- AddDeletePage.java
|       |       |   |-- BasicAuthPage.java
|       |       |   |-- BrokenImagesPage.java
|       |       |   |-- ChallengingDomPage.java
|       |       |   |-- CheckBoxPage.java
|       |       |   |-- ContextMenuPage.java
|       |       |   |-- DigestAuthPage.java
|       |       |   |-- DisappearingElementsPage.java
|       |       |   |-- DragDropPage.java
|       |       |   |-- DropDownPage.java
|       |       |   |-- DynamicContentPage.java
|       |       |   |-- DynamicControlsPage.java
|       |       |   |-- DynamicElementPage.java
|       |       |   |-- EntryAdPage.java
|       |       |   |-- FileDownloadPage.java
|       |       |   |-- FileUploadPage.java
|       |       |   |-- FloatingMenuPage.java
|       |       |   |-- ForgotPasswordPage.java
|       |       |   |-- FormAuthPage.java
|       |       |   |-- FrameInlinePage.java
|       |       |   |-- FrameNestedPage.java
|       |       |   |-- GeolocationPage.java
|       |       |   |-- HomePage.java
|       |       |   |-- HorizontalSliderPage.java
|       |       |   |-- HoversPage.java
|       |       |   |-- InfiniteScrollPage.java
|       |       |   |-- InputsPage.java
|       |       |   |-- JqueryuiPage.java
|       |       |   |-- JSAlertsPage.java
|       |       |   |-- JSErrorPage.java
|       |       |   |-- KeyPressPage.java
|       |       |   |-- LargeDeepDomPage.java
|       |       |   |-- NewWindowPage.java
|       |       |   |-- NotificationPage.java
|       |       |   |-- RedirectionPage.java
|       |       |   |-- SecureDownloadPage.java
|       |       |   |-- ShadowDomPage.java
|       |       |   |-- ShiftingContentMenuPage.java
|       |       |   `-- StatusCodesPage.java
|       |       |-- tests
|       |       |   |-- ABTestingTest.java
|       |       |   |-- AddDeleteTest.java
|       |       |   |-- BasicAuthTest.java
|       |       |   |-- BrokenImagesTest.java
|       |       |   |-- ChallengingDomTest.java
|       |       |   |-- CheckboxTest.java
|       |       |   |-- ContextMenuTest.java
|       |       |   |-- DigestAuthTest.java
|       |       |   |-- DisappearingElementsTest.java
|       |       |   |-- DragDropTest.java
|       |       |   |-- DropDownTest.java
|       |       |   |-- DynamicContentTest.java
|       |       |   |-- DynamicControlsTest.java
|       |       |   |-- DynamicElementTest.java
|       |       |   |-- EntryAdTest.java
|       |       |   |-- FileDownloadTest.java
|       |       |   |-- FileUploadTest.java
|       |       |   |-- FloatingMenuTest.java
|       |       |   |-- ForgotPasswordTest.java
|       |       |   |-- FormAuthTest.java
|       |       |   |-- FrameInlineTest.java
|       |       |   |-- FrameNestedTest.java
|       |       |   |-- GeolocationTest.java
|       |       |   |-- HomeTest.java
|       |       |   |-- HorizontalSliderTest.java
|       |       |   |-- HoversTest.java
|       |       |   |-- InfiniteScrollTest.java
|       |       |   |-- InputsTest.java
|       |       |   |-- JqueryuiTest.java
|       |       |   |-- JSAlertsTest.java
|       |       |   |-- JSErrorTest.java
|       |       |   |-- KeyPressTest.java
|       |       |   |-- LargeDeepDomTest.java
|       |       |   |-- NewWindowTest.java
|       |       |   |-- NotificationTest.java
|       |       |   |-- RedirectionTest.java
|       |       |   |-- SecureDownloadTest.java
|       |       |   |-- ShadowDomTest.java
|       |       |   |-- ShiftingContentMenuTest.java
|       |       |   `-- StatusCodesTest.java
|       |       `-- utilities
|       |           |-- CsvUtils.java
|       |           |-- ExcelUtils.java
|       |           |-- JsonUtils.java
|       |           |-- ScreenshotUtililty.java
|       |           `-- TestDataProvider.java
|       `-- resources
|           |-- configuration.properties
|           |-- uploadTestFile.txt
|           `-- testdata
|               |-- loginData.json
|               `-- ChallengingDomData.xlsx
|-- target
|   |-- allure-results
|   |-- screenshots
|   `-- surefire-reports
`-- logs
```

## Technologies Used

- Java
- Maven
- Playwright Java
- TestNG
- Allure TestNG
- AspectJ Weaver
- SLF4J
- Logback
- Jackson
- Apache POI
- Apache Commons CSV
- Lombok

## Setup

### 1. Install Java

This project is currently configured with:

```xml
<maven.compiler.release>25</maven.compiler.release>
```

Install JDK 25 or update the Java version in `pom.xml` according to your local setup.

Verify Java installation:

```bash
java -version
```

### 2. Install Maven

Verify Maven installation:

```bash
mvn -version
```

### 3. Install project dependencies

```bash
mvn clean install -DskipTests
```

### 4. Install Playwright browsers

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

## Configuration

Main configuration file:

```text
src/test/resources/configuration.properties
```

Important settings:

```properties
browser = CHROMIUM
headless=true
maxRetryCount = 2
screenshotPath = target/screenshots/
```

Supported browsers:

```properties
browser = CHROMIUM
browser = FIREFOX
browser = WEBKIT
```

To run tests in headless mode:

```properties
headless=true
```

To run tests in headed mode:

```properties
headless=false
```

## Run Tests

### Run all tests

```bash
mvn test
```

### Run tests using TestNG suite

The project uses `testng.xml` through Maven Surefire.

```bash
mvn test
```

### Run smoke tests

```bash
mvn test -Dgroups=smoke
```

### Run regression tests

```bash
mvn test -Dgroups=regression
```

### Run a specific test class

```bash
mvn test -Dtest=HomeTest
```

Example:

```bash
mvn test -Dtest=DigestAuthTest
```

### Run a specific test method

```bash
mvn test -Dtest=HomeTest#homeTest
```

## Parallel Execution

Parallel execution is configured in `testng.xml`:

```xml
<suite name="QA-Automation-Suite" verbose="10" parallel="methods" thread-count="2">
```

Current configuration:

```text
parallel = methods
thread-count = 2
```

To change parallel execution, update `testng.xml`.

Example:

```xml
<suite name="QA-Automation-Suite" verbose="10" parallel="methods" thread-count="4">
```

## Test Groups

Tests are grouped using TestNG groups:

```java
@Test(groups = "smoke")
@Test(groups = "regression")
```

The current `testng.xml` includes both:

```xml
<groups>
    <run>
        <include name="smoke"/>
        <include name="regression"/>
    </run>
</groups>
```

## Allure Report

### Generate Allure results

Allure results are generated automatically under:

```text
target/allure-results
```

Run tests:

```bash
mvn test
```

### Serve Allure report

```bash
mvn allure:serve
```

### Generate Allure report

```bash
mvn allure:report
```

Generated report location:

```text
target/site/allure-maven-plugin
```

## Screenshots

Screenshots are captured on test failure by the TestNG listener.

Screenshot path:

```text
target/screenshots/
```

The path is configured in:

```properties
screenshotPath = target/screenshots/
```

## Reports

### Surefire reports

```text
target/surefire-reports
```

### Allure results

```text
target/allure-results
```

### Screenshots

```text
target/screenshots
```

### Logs

```text
logs/basic/test-execution.log
```

## Retry Logic

Retry count is configured in:

```text
src/test/resources/configuration.properties
```

```properties
maxRetryCount = 2
```

Retry implementation:

```text
src/test/java/com/techbytes/qa/listeners/RetryAnalyzer.java
```

Tests use retry logic like this:

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
```

## Test Data

### JSON test data

```text
src/test/resources/testdata/loginData.json
```

### Excel test data

```text
src/test/resources/testdata/ChallengingDomData.xlsx
```

### Upload test file

```text
src/test/resources/uploadTestFile.txt
```

## Useful Maven Commands

### Clean project

```bash
mvn clean
```

### Compile project

```bash
mvn compile
```

### Compile tests

```bash
mvn test-compile
```

### Run tests

```bash
mvn test
```

### Clean and run tests

```bash
mvn clean test
```

### Skip tests during build

```bash
mvn clean install -DskipTests
```

## Notes

- Browser selection is controlled from `configuration.properties`.
- Test execution is controlled by `testng.xml`.
- Allure results are written to `target/allure-results`.
- Failed test screenshots are saved to `target/screenshots`.
- The framework currently targets `https://the-internet.herokuapp.com`.
