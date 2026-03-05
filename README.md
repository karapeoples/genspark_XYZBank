# XYZ Bank Test Automation Framework
### GenSpark Training Project (Java + TestNG)

This repository contains a **UI test automation framework built during GenSpark training** using **Java**, **TestNG**, and the **Page Object pattern**.

The project demonstrates structured automation practices including reusable utilities, configurable test setup, listeners for retry logic, and automated report generation.

The automation suite targets the **XYZ Bank demo application**, commonly used for automation training scenarios.

---

## Highlights

- ✅ **TestNG** test framework and suite execution
- ✅ **Page Object / Page Factory** design pattern
- ✅ Reusable **utility classes** for driver control, waits, and actions
- ✅ **Retry logic** and **listeners** for improved test stability
- ✅ Configurable test environment via properties files
- ✅ Automated **Surefire + TestNG reports**
- ✅ Supporting documentation artifact: `TestInfoBook.xlsx`

---

## Project Structure
src/test/java
├─ BankManagerTests.java
├─ CustomerTests.java
├─ BaseTest.java

├─ pages/
│ ├─ CustomerTestPageFactory.java
│ └─ ManagerTestPageFactory.java

├─ listeners/
│ ├─ AnnotationTransformer.java
│ ├─ RetryAnalyzer.java
│ └─ TestListeners.java

├─ utils/
│ ├─ ClickUtil.java
│ ├─ DriverUtil.java
│ ├─ ExecUtils.java
│ ├─ LoadProperties.java
│ ├─ SetUtil.java
│ └─ WaitUtil.java

src/test/resources
├─ log4j.properties
└─ properties.properties


---

## Test Coverage

### Bank Manager Tests
Automation covering manager-side workflows including:

- customer management flows
- validating UI interactions and expected outcomes

### Customer Tests

Automation covering user-side flows such as:

- customer login scenarios
- navigation and UI validation checks

Test scenarios are defined within the individual test classes.

---

## How to Run

### Requirements

- Java (JDK 8 or higher)
- Maven
- Browser driver compatible with your local environment

---

### Install Dependencies

```bash
mvn clean install

Run Test Suite

mvn test

Test Reports

After execution, reports are generated under:
target/surefire-reports/

These reports include:

~ execution summaries
~ passed/failed tests
~ stack traces for debugging
~ execution timing

TestNG HTML reports can be opened locally to review detailed results.

Configuration

Project configuration is managed through:
src/test/resources/properties.properties

Logging configuration:
src/test/resources/log4j.properties

These files control the environment setup and logging output.

Documentation Artifact

This repository also includes a planning/reference artifact:
TestInfoBook.xlsx

This workbook can be used to track:

~ test scenarios
~ expected results
~ execution notes
~ potential automation expansion

Why This Repo Is Pinned

This project demonstrates:

~ structured UI automation architecture
~ Page Object design
~ reusable testing utilities
~ automated reporting
~ documentation habits within automation projects

It complements my technical documentation portfolio by showing how I structure and document test automation systems.

Author

Kara R. Peoples
📫 karaepeoples.dev@gmail.com
💼 https://www.linkedin.com/in/karapeoples
