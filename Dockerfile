# Base Image
FROM maven:3.8.3-openjdk-17

# Environment variable for the browser
ENV BROWSER chrome
ENV RUNNER = runner.xml
ENV TAG = "@regression"
ENV THREAD_COUNT = 3
# Working Directory in the Container
WORKDIR /home/webStudyCase

COPY src /home/webStudyCase/src
COPY pom.xml /home/webStudyCase
COPY runner.xml /home/webStudyCase
COPY target /home/webStudyCase/target

# Run tests
CMD mvn test -Dbrowser=${BROWSER} -Dsurefire.suiteXmlFiles=${RUNNER} -Ddataproviderthreadcount=${THREAD_COUNT} -Dcucumber.filter.tags="${TAG}"

