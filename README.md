# Selenium StudyCase

## Installation

To run this project on your local machine, follow these steps:

1. Clone the repo: `git clone https://github.com/yahyakara/webStudyCase`
2. Navigate to the folder

## Running the Tests

### Parameters

* BROWSER : Browser type that you want to run test
* surefire.suiteXmlFiles : Runner type "runner.xml"
* TAG : Cucumber tag to filter scenarios that you want to run
* THREAD_COUNT : Parallel thread count we should set it based on server to run the tests properly

### To run on docker

```sh
cd /webStudyCase
BROWSER=chrome_remote RUNNER=runner.xml TAG="@login" docker-compose up --build
```

### To run on local server with mavne

```sh
cd /webStudyCase
 mvn clean test -Dbrowser=chrome -Dsurefire.suiteXmlFiles=runner.xml -Dcucumber.filter.tags="@login" -Ddataproviderthreadcount=3
```

## Reports

### For docker running

* http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html?redirect=false

### For local running

* Allure report (Allure should be installed on local machine)

```sh
cd target
allure serve
```

## Dependencies

* Selenium
* Cucumber
* TestNg
* Allure report
* Rest Assured
* Gson
* Lombok
* Poi
* Log4j

## Cases
* The status and loading times of boutique links and their associated images are reported in an Excel file.
* Login scenarios are covered using the Cucumber Data-Driven method.
* The tests can be executed in Docker with a single command.
* The failed scenarios are reported with screenshots.