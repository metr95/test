# epamTest
test task for epam

# Prerequisites
you need jdk and maven to be installed
check jdk version witn cmd and command >java -version
if you don't java installed go to https://www.oracle.com/technetwork/java/javase/downloads/index.html and donwload and install jdk 8

check if maven is installed in your system with command >mvn -version
if maven is not installed go to https://maven.apache.org/download.cgi and donwload latest build
refer to https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows for maven installation instructions

# running the tests
use cmd to run the tests
in cmd navigate to folder "epamTest"
type command >"mvn clean test" in order to run all the tests.
if you want to run some testSet use command >"mvn clean -Dtest=<classNameOfTestSet> test"
if you want to run some special case use command >"mvn clean -Dtest=<classNameOfTestSet#TestMethodName>"
to see report go to \epamTest\target\surefire-reports\index.html OR to \epamTest\target\surefire-reports\emailable-report.html
to run tests in parallel use -DthreadCount=N command in cmd
