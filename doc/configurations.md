# Run Configuration
There are three possible ways of setting up test configuration options. In order of lowest to highest precedence: property files > command line parameters.

## Selenium settings and run arguments
A full list of the available settings can be found in the [GitHub Wiki](https://github.com/rivm-syso/SeleniumTestTemplate/wiki) page or in the `SeleniumDriverWrapper.class` from the Selenium Test Framework.

Custom settings and run arguments can be added by simply declaring the settings name:
```java
public static String TEST_APP_PATH = "test.app.path";
```

Then during runtime the value of this argument can be retrieved by calling the base methods like this:
```java
base.getRunParameterAsString(generic.TEST_APP_PATH);
base.getRunParameterAsInt(generic.TEST_APP_PATH);
base.getRunParameterAsBoolean(generic.TEST_APP_PATH);
```

## Property Files

### Selenium Properties

***Important*** Do not use these properties files to set sensitive login information or test URL's.

The Selenium Test Framework has a default `selenium_base.properties` file containing the following standard set of parameters:
```ini
settings.usebrowser = chrome
settings.window.maximize = true
# Start driver / browser in headless mode
settings.driver.headless = false
# Always assert that user input was set correctly
settings.implicit.verify = false

# Default timeout values
# in milliseconds
settings.pageload.wait = 9000
# Keep this at 100 or above for stability
settings.sync.sleep = 100
# in seconds
settings.sync.timeout = 3
settings.notification.timeout = 9

# Debug settings
settings.objects.highlight = false
```
A custom properties file can be placed in `../src/test/java/nl/overheid/projectname/base/selenium_base.properties`. The settings from this file will override the default file from the STF.
Specific options requiring different values in a Linux environment are automatically overridden with values from the additional `selenium_linux.properties`.

### Credential Properties
The user name and passwords could be defined in a properties file called `test.users.properties`.
Use the following properties naming convention:
```ini
admin.username = [name]
admin.password = [pass]
```
The runner argument for this file is `test.users=[file path]`.

### Command Line
This is the preferred method of passing in sensitive data like a base URL containing user credentials for basic authentication: `-Dtest.app.path=https://<user>:<pass>@test.domain.example`.

Or for providing the path to the property file containing the user credentials, which could differ between test environments: `-Dtest.users=/home/test/resources/test.users.properties`.
Instead of making a properties file for credentials, these can also be specified as a run argument, for example: `-Dadmin.username=exampleName`

In Eclipse these can be set as `VM arguments` in your run configurations.

## Test Classes, Tags and Reporting
The complete set of automated UI tests is usually scheduled to run after a nightly build completes and executed using a single maven command from jenkins. However, this a bit too much for developing and maintaining specific tests. So, each product's test set can also be executed individually (conveniently from within eclipse) by running one of the following test classes as a JUnit test:

## Runner Classes
Runner classes should extend the `CucumberBase` class to take care of properly starting up and shutting down selenium driver instances. An example can be found in `src/test/java` > `nl.overheid.projectname` package.

### Tags
By default all of the product specific scripts mentioned above are set up to run the complete set of tests identified by the `@nightly` tag and can not have the tag `@ignore`. Feature files may also contain a list of relevant tags to facilitate more fine grained testexecution of specific features like `@end-to-end`, `@fire` and `@smoke` for example. If so required test execution can be limited to a single test scenario by applying a unique tag to a specific scenario in the cucumber feature file.
If you want to run a specified scenario but you do not want to change your test code you could specify the unique tag in the run parameter `-Dcucumber.filter.tags`. For Example: `-Dcucumber.filter.tags=@unique`, will only run the scenarios with that specified tag.

#### Functional runner tags
- @ui      : This tag will enable screenshots when a scenario fails. This should be used for Web applications that have a UI or for applications that requires a WebDriver.

#### Aesthetic runner tags
- @nightly : This is the preferred runner tag to use for selected features and scenarios as part of the nightly test build.
- @ignore  : This is the preferred runner tag to use for selected features and scenarios that are not part of the nightly test build.
- @api     : This is a preferred runner tag to use for your API features and scenarios.
- @shell   : This is a preferred runner tag to use for your Shell script features and scenarios.

## Reports
Alongside the default HTML report the test runners are set up to generate JSON files with test results. The HTML reports are primarily useful for running tests locally and capture screenshots in case a test fails. The JSON files are used to gather the same results per product and feed the [Cucumber Reports](https://plugins.jenkins.io/cucumber-reports) Jenkins plugin. These JSON files contain the same test results (including screenshots) but is more powerful since Jenkins can keeps track of test results over time which allows for trend analysis.

## Logging
The default log level for the framework is set to `DEBUG` in [log4j.properties](../src/main/resources/log4j.properties). This setting is inherited by projects which include the framework as a dependency. If needed, these settings can be overridden simply by setting up a copy of the properties file in the resources folder of the inheriting project, most likely `src/test/resources`.
