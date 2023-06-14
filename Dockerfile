# Base Image
FROM maven:3.8.3-openjdk-17

# Environment variable for the browser
ENV BROWSER chrome
ENV RUNNER = runner.xml
ENV TAG = "@regression"
# Working Directory in the Container
WORKDIR /home/webStudyCase

COPY src /home/webStudyCase/src
COPY pom.xml /home/webStudyCase
COPY runner.xml /home/webStudyCase
COPY target /home/webStudyCase/target

# Run tests
CMD mvn test -Dbrowser=${BROWSER} -Dsurefire.suiteXmlFiles=${RUNNER} -Ddataproviderthreadcount=3 -Dcucumber.filter.tags="${TAG}"

