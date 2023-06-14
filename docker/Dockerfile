# Base Image
FROM maven:3.8.3-openjdk-17

# Environment variable for the browser
ENV BROWSER chrome
ENV RUNNER = runner.xml
# Working Directory in the Container
WORKDIR /home/webStudyCase

COPY webStudyCase/src /home/webStudyCase/src
COPY webStudyCase/pom.xml /home/webStudyCase
COPY runner.xml /home/webStudyCase


# Run tests
CMD mvn clean test -Dbrowser=${BROWSER} -Dkeyword="${KEYWORD}" -Dsurefire.suiteXmlFiles="${RUNNER}"

