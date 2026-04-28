# Selenium Java Framework

UI automation framework for [The Internet](https://the-internet.herokuapp.com) using Selenium WebDriver, Java, Maven, TestNG, and Allure reporting.

## Features

- Selenium WebDriver with Java
- TestNG test framework
- Maven build management
- Page Object Model
- Shared base test setup and teardown
- Browser factory with thread-local WebDriver instances
- Browser selection using `configuration.properties`
- WebDriver binaries managed by Selenium/WebDriverManager support
- Allure reporting
- Screenshot capture on test failure
- Retry logic with TestNG `IRetryAnalyzer`
- Parallel execution using TestNG suite configuration
- Test groups for smoke and regression
- JSON test data support
- Excel and CSV utility support
- Logging with SLF4J and Logback

## Project Structure

```text
java-selenium
|-- pom.xml
|-- testng.xml
|-- nbactions.xml
|-- README.md
|-- src
|   |-- main
|   |   |-- java
|   |   |   `-- com/techbytes/qa
|   |   |       |-- HerokuappProject.java
|   |   |       |-- core
|   |   |       |-- listeners
|   |   |       |-- pages
|   |   |       |   |-- ABTestingPage.java
|   |   |       |   |-- AddDeletePage.java
|   |   |       |   |-- BasicAuthPage.java
|   |   |       |   |-- BrokenImagesPage.java
|   |   |       |   |-- ChallengingDomPage.java
|   |   |       |   |-- CheckBoxPage.java
|   |   |       |   |-- ContextMenuPage.java
|   |   |       |   |-- DigestAuthPage.java
|   |   |       |   |-- DisappearingElementsPage.java
|   |   |       |   |-- DragDropPage.java
|   |   |       |   |-- DropDownPage.java
|   |   |       |   |-- DynamicContentPage.java
|   |   |       |   |-- DynamicControlsPage.java
|   |   |       |   |-- DynamicElementPage.java
|   |   |       |   |-- EntryAdPage.java
|   |   |       |   |-- FileDownloadPage.java
|   |   |       |   |-- FileUploadPage.java
|   |   |       |   |-- FloatingMenuPage.java
|   |   |       |   |-- ForgotPasswordPage.java
|   |   |       |   |-- FormAuthPage.java
|   |   |       |   |-- FrameInlinePage.java
|   |   |       |   |-- FrameNestedPage.java
|   |   |       |   |-- GeolocationPage.java
|   |   |       |   |-- HorizontalSliderPage.java
|   |   |       |   |-- HoversPage.java
|   |   |       |   |-- InfiniteScrollPage.java
|   |   |       |   |-- InputsPage.java
|   |   |       |   |-- JqueryuiPage.java
|   |   |       |   |-- JSAlertsPage.java
|   |   |       |   |-- KeyPressPage.java
|   |   |       |   |-- LargeDeepDomPage.java
|   |   |       |   |-- NewWindowPage.java
|   |   |       |   |-- NotificationPage.java
|   |   |       |   |-- RedirectionPage.java
|   |   |       |   |-- SecureDownloadPage.java
|   |   |       |   |-- ShadowDomPage.java
|   |   |       |   |-- ShiftingContentMenuPage.java
|   |   |       |   `-- StatusCodesPage.java
|   |   |       `-- utilities
|   |   `-- resources
|   |       |-- configuration.properties
|   |       `-- logback.xml
|   `-- test
|       |-- java
|       |   `-- com/techbytes/qa
|       |       |-- ABTestingTest.java
|       |       |-- AddDeleteTest.java
|       |       |-- BasicAuthTest.java
|       |       |-- BrokenImagesTest.java
|       |       |-- ChallengingDomTest.java
|       |       |-- CheckBoxTest.java
|       |       |-- ContextMenuTest.java
|       |       |-- DigestAuthTest.java
|       |       |-- DisappearingElementsTest.java
|       |       |-- DragDropTest.java
|       |       |-- DropDownTest.java
|       |       |-- DynamicContentTest.java
|       |       |-- DynamicControlsTest.java
|       |       |-- DynamicElementTest.java
|       |       |-- EntryAdTest.java
|       |       |-- FileDownloadTest.java
|       |       |-- FileUploadTest.java
|       |       |-- FloatingMenuTest.java
|       |       |-- ForgotPasswordTest.java
|       |       |-- FormAuthTest.java
|       |       |-- FrameInlineTest.java
|       |       |-- FrameNestedTest.java
|       |       |-- GeolocationTest.java
|       |       |-- HorizontalSliderTest.java
|       |       |-- HoversTest.java
|       |       |-- InfiniteScrollTest.java
|       |       |-- InputsTest.java
|       |       |-- JqueryuiTest.java
|       |       |-- JSAlertsTest.java
|       |       |-- KeyPressTest.java
|       |       |-- LargeDeepDomTest.java
|       |       |-- NewWindowTest.java
|       |       |-- NotificationTest.java
|       |       |-- RedirectionTest.java
|       |       |-- SecureDownloadTest.java
|       |       |-- ShadowDomTest.java
|       |       |-- ShiftingContentMenuTest.java
|       |       `-- StatusCodesTest.java
|       `-- resources
|           |-- uploadTestFile.txt
|           `-- testData
|               `-- loginData.json
`-- target
    |-- allure-results
    |-- screenshots
    `-- surefire-reports
```

## Technologies Used

- Java
- Maven
- Selenium WebDriver
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

Install JDK 25 or update the Java version in `pom.xml` for your local setup.

Verify Java installation:

```bash
java -version
```

### 2. Install Maven

Verify Maven installation:

```bash
mvn -version
```

### 3. Install browser drivers

Selenium 4 can manage browser drivers through Selenium Manager. The project also includes WebDriverManager as a dependency.

Make sure at least one supported browser is installed locally:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

### 4. Install project dependencies

```bash
mvn clean install -DskipTests
```

## Configuration

Main configuration file:

```text
src/main/resources/configuration.properties
```

Important settings:

```properties
baseurl = https://the-internet.herokuapp.com
browser = CHROME
implicitWait = 5
explicitWait = 10
maxRetryCount = 2
screenshotPath = target/screenshots/
jsonDataPath = testdata/loginData.json
```

Supported browsers:

```properties
browser = CHROME
browser = FIREFOX
browser = EDGE
```

The framework loads application URLs, timeout values, retry count, screenshot path, and data paths from this properties file.

## Run Tests

### Run all tests

```bash
mvn test
```

### Run tests using TestNG suite

Maven Surefire is configured to use `testng.xml`.

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
mvn test -Dtest=ABTestingTest
```

Example:

```bash
mvn test -Dtest=DigestAuthTest
```

### Run a specific test method

```bash
mvn test -Dtest=ABTestingTest#ABTest
```

## Parallel Execution

Parallel execution is configured in `testng.xml`:

```xml
<suite name="QA Automation Suite" parallel="methods" thread-count="2">
```

Current configuration:

```text
parallel = methods
thread-count = 2
```

To change parallel execution, update `testng.xml`.

Example:

```xml
<suite name="QA Automation Suite" parallel="methods" thread-count="4">
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

Log output is configured in:

```text
src/main/resources/logback.xml
```

## Retry Logic

Retry count is configured in:

```text
src/main/resources/configuration.properties
```

```properties
maxRetryCount = 2
```

Retry implementation:

```text
src/main/java/com/techbytes/qa/listeners/RetryAnalyzer.java
```

Tests use retry logic like this:

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
```

## Test Data

### JSON test data

```text
src/test/resources/testData/loginData.json
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

- Browser selection is controlled from `src/main/resources/configuration.properties`.
- Test execution is controlled by `testng.xml`.
- Allure results are written to `target/allure-results`.
- Failed test screenshots are saved to `target/screenshots`.
- The framework currently targets `https://the-internet.herokuapp.com`.
