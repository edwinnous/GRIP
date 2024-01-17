# SeleniumTestTemplate
This is a template repository intended to assist in a convenient set up of new test automation projects based on the Selenium Test Framework. The Selenium Test Framework is a generic Selenium/Cucumber test framework built on Java. This template provides a step-by-step set-up guide, configuration options, Selenium / API test-functionality, example step definitions and feature files. The basic implementation examples allow for a quick setup of a test base to be used in various test-automation solutions.

The Framework currently requires Java 11 or higher to run and implements Selenium 4.12.0, Cucumber 6.4, JUnit 4.12 and RestAssured 4.5.1.

Besides the README documentation there is also information available on the [GitHub Wiki](https://github.com/rivm-syso/SeleniumTestTemplate/wiki) page of this project. The source code also provides detailed descriptions on how to use public methods and the expected outcome. All methods from the Selenium Test Framework are also available as Javadoc.

Current supported STF version is **`1.4.3`**

## Quick Links to Documentation
- [BrowserStack](doc/browserstack.md)
- [Jenkins](doc/jenkins.md)
- [Jenkins Docker setup](doc/docker.md)
- [Maven configurations](doc/maven.md)
- [Release notes](doc/releasenotes.md)
- [Run configurations](doc/configurations.md)
- [Style Guide](doc/styleguide.md)
- [Windows or Campus VDI setup](doc/windows.md)


## Minimal Requirements
These are the minimal technical requirements you need to have to work with the Selenium Test Framework and run Selenium test scenarios:
- Java 11 [Oracle](https://www.oracle.com/nl/java/technologies/javase-downloads.html)
- Java IDE [Eclipse IDE for Enterprise Java](https://www.eclipse.org/downloads/)
- Cucumber Eclipse [Plugin](https://marketplace.eclipse.org/content/cucumber-eclipse-plugin)
- Apache Maven [latest](https://maven.apache.org/download.cgi)
- GitHub PAT with at least `public_repo` and `read:packages` for the Selenium Test Framework package dependency [GitHub Docs](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

## Quick Start
All java related files like test runner-classes and step definitions are available in the folder [src/test/java](src/test/java). The accompanying feature files and config files are available from [src/test/resources](src/test/resources). With the minimal requirements installed everything should work out-of-the-box.

### Project Setup
1. Generate a new Github repository from this template using the `Use this template` button in Github or copy the code
2. Import the project in Eclipse as `Existing maven project`
3. Set up a `settings.xml` in the `/Users/../.m2` (Windows) folder with your PAT and provided package location
4. Update the project in Eclipse by right-clicking on the root of the project and selecting `Maven` > `Update Project`

### Example Testrun Cucumber / Selenium
1. Set up the JVM run parameters for the `ProjectNameCucumberSuiteTest` class under the `projectname` package. At least the run argument `-Dtest.app.path=https://www.rivm.nl` is required for running UI tests.
2. Start the UI example test by running the `ProjectNameCucumberSuiteTest` class.
3. A Cucumber report can be reviewed by opening the `index_e2e.html` in a web browser, this report is placed under the folder `target/nightly/projectname`.

### Example Testrun RestAssured
1. Set up the JVM run parameters for the `ProjectNameRestSuiteTest` class under the `projectname` package. At least the run argument `-Dapi.base.uri=http://test.rivmweb.rivm.nl` is required for running headless API tests.
2. Start the API example test by running the `ProjectNameRestSuiteTest` class.
3. A Cucumber report can be reviewed by opening the `index_api.html` in a web browser, this report is placed under the folder `target/nightly/projectname`.


## Starting a new Test Automation Project
This is a step-by-step guide to set up a new test automation project with this template:
1. To start a new test automation project you need to either generate a new repository to your personal github account using the 'Use this template' button in this Github repository or copy the repository code. Steps to set up a GitHub SSH key in the Campus VDI are detailed [here](doc/windows.md).
2. Make sure to download and install the technical requirements listed under the section `Minimal Requirements`. 
3. Once the repository contents are downloaded a Maven `settings.xml` file is required on the local machine. This file will point towards the correct repository containing the Selenium Test Framework. Setup instructions for this file are available in the [Maven documentation](doc/maven.md).
4. Import the project in Eclipse as `Existing maven project`.
The [pom.xml](pom.xml) should take care of retrieving essential libraries for the project like Selenium, Cucumber and JUnit. Working with an editor lacking pom support would require these libraries to be installed manually, which is beyond the scope of this template and not supported.
5. To ensure all libraries are installed correctly right-click the root of the project and select `Maven` > `Update Project...` to fetch and install all necessary libraries.
6. To ensure that the Eclipse Cucumber Plugin properly recognizes feature files the project needs to be converted to a Cucumber Project. In `Project Explorer` right-click the root of the project and from the drop-down menu select `Configure` > `Convert to Cucumber Project...`.

The local repository and environment is now ready to be used to build a custom test automation solution with the Selenium Test Framework as it's foundation.

### Running the Example Test
Set up the run parameters for the `ProjectNameCucumberSuiteTest` class under the `base` package:
1. Add a new `JUnit` run configuration by right-clicking the `ProjectNameCucumberSuiteTest.java` and select `Properties` > `New` > `JUnit`
2. Select `JUnit 4` as `Test runner`
3. Add run arguments by selecting the `Arguments` tab and add the following `VM argument`: `-Dtest.app.path=https://www.rivm.nl`.
4. Run the example test as a `JUnit test` by starting the `ProjectNameCucumberSuiteTest` class in `src/test/java` > `nl.overheid.projectname`.
5. A Cucumber report can be reviewed by opening the `index.html` in a web browser, all reports are placed in the `target/nightly/projectname` folder.

### Refactoring the `ProjectName`
1. Refactor the project name and artifact ID to your project name: 
In `Project Explorer` right-click the root of the project, from the drop-down menu select `Refactor` > `Rename` to Rename your project. Do the same for `Rename Maven Artifact` and change the `Artifact Id`, this will most likely be the same as your project name.
2. Refactor the package name:
In `Project Explorer` right-click the root package `nl.overheid.projectname` in `src/test/java`, from the drop-down menu select `Refactor` > `Rename` to Rename your package. Replace `projectname` with the name of your project (only lower-case is allowed), tick all the boxes and click on `Preview` > `OK`.
3. Refactor the TestBase name:
Refactor the `ProjectNameTestBase.java` class in the `nl.overheid.projectname.base` package Right click on the class and select `Refactor` > `Rename` and replace `ProjectName` with the name of your project. Then click on `Next` > `Finish`.
4. Refactor the Runner class:
Refactor the runner class in the `nl.overheid.projectname` package. Right click on the class `ProjectNameCucumberSuiteTest` and select `Refactor` > `Rename` and replace `ProjectName` with the name of your project. Then click on `Next` > `Finish`. Running multiple runner classes is quite common and can be named anything, as long as they end with `Test` so maven can recognize them as such.
Change the folder in where the report will be placed by replacing `/projectname` with your own project name in the class:
```java
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/nightly/projectname/index.html",
        "json:target/nightly/projectname/nightly_report.json"
```
If you use multiple runner classes then they should get there own folder so they can be easily aggregated into the report. For example:
```java
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/nightly/projectname/groupname1/index.html",
        "json:target/nightly/projectname/groupname1/nightly_report.json"
```

## File structure and Class Conventions
For ease of use and support it is considered best practice to adhere to the basic file structure and class placement conventions of the framework.
- `src/test/java` contains the project test code with the following package structure
    - `nl.overheid.projectname` the root package contains the runner classes
    - `nl.overheid.projectname.base` contains a project specific `TestBase` class
    - `nl.overheid.projectname.shared` contains the shared hook classes
    - `nl.overheid.projectname.shared.actions | stepdef` contains all shared functionality actions and step definitions
    - `nl.overheid.projectname.example.actions | stepdef` contains grouped functionality actions and step definitions
- `src/test/resources` contains cucumber features, configuration and test data files
    - `config` folder will contain configuration files
    - Features and test data (.zip, .jpeg, etc.) files can be grouped in folders
More information on code styling can be found [here](doc/styleguide.md).

## Working with the Selenium Test Framework
Working examples of the implementations can be found in `nl.overheid.projectname.example.action`.

### FunctionalTestBase
The main library of generic functionality can be found in the FunctionalTestBase(FTB) class. The FTB is provided as Javadoc and more information can be found in this projects [GitHub Wiki](https://github.com/rivm-syso/SeleniumTestTemplate/wiki) page.
To use the FTB you need to extend your projects test base, this is already provided in the template code and can be found in `nl.overheid.projectname.base`.
To use the FTB and your own TestBase class you'll need to import the following:
```java
import nl.overheid.tryout.base.ProjectNameTestBase;
```
Then declare the class variables:
```java
    private final ProjectNameTestBase base;
```
And instantiate your TestBase class:
```java
  public ExampleFunctionalityAction() {
    base = new ProjectNameTestBase();
  }
```
Now all methods from the FTB and your own TestBase can be called upon by using `base.`. 

### RestAssuredWrapper
Running tests against a REST-API is simply a matter of importing the RestAssuredWrapper class and calling one of the default methods provided, as is demonstrated in the `RestAssuredStepDef.java`. The default methods available are Delete, Get, Options, Patch, Post and Put. Depending on the need for credentials all methods come in two flavors for both authorized and unauthorized requests. When these default methods are insufficient for specific test scenario's then a custom request can be build from the ground up by using the `getRequestSpec()` method.

### WebDriver
To use the Selenium WebDriver functionality you need to add the following to your class:
```java
import org.openqa.selenium.WebDriver;
...
public class ExampleFunctionalityAction {
  private WebDriver driver;
...
  public ExampleFunctionalityAction() {
...
    driver = SeleniumDriverWrapper.getInstance.getDriver();
  }
}
```

### Assertions
The framework uses JUnit Assert, to use Assert you need to import:
```java
import org.junit.Assert;
```

### Debug Logging
The framework uses the slf4j LoggerFactory to log debug messages as output in the console. To use the logger you need to import:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```
And instantiate the logger in your class:
```java
private static final Logger LOG = LoggerFactory.getLogger(ExampleFunctionalityAction.class);
```

### Run Configuration
Detailed instructions on setting up the projects run configurations are available [here](doc/configurations.md).

### Helper Classes
The Functional Test Base has a few helper classes that can be implemented, these classes consist of non-generic Selenium functionality or functionality not provided by Selenium.

#### Credential Manager
A basic implementation of the `CredentialManager` can be found in `nl.overheid.projectname.shared.stepdef | actions` > `LoginInStepDef | Action`.

How to set up a file with credentials is described in more detail in the [configurations](doc/configurations.md) documentation.
In short, the user name and password can be retrieved by using the following methods:
```java
base.getUserNameForRole(user);
base.getUserPassForRole(user);
```
By calling these methods the credential manager will try to locate the correct credential from three potential sources. In order of least to most preferred: `test.users` properties file --> specified environment variable (Jenkins plugin) --> run parameter. When a variable is set in multiple locations then the most preferred source will take precedence.

#### Lorem Ipsum Library
A basic implementation of the `LoremLibrary` can be found in `nl.overheid.projectname.base` > `ProjectNameTestBase`.

To access the methods in this helper class first declare and instantiate the library as follows:
```java
public class ProjectNameTestBase extends FunctionalTestBase{

private final LoremLibrary lorem;

  public ProjectNameTestBase() {
    lorem = LoremLibrary.getInstance();
  }
}
```

Then calling `lorem.paragraph();` will return a standardized lorem ipsum text of 100 words.
A full list of methods can be found in the library itself. The text that is returned is standardized, this means that if you get 75 words you will always get the same 75 words.

#### Randomizer
A basic implementation of the `Randomizer` can be found in `nl.overheid.projectname.base` > `ProjectNameTestBase`.

To access the methods in this helper class first declare and instantiate the library as follows:
```java
public class ProjectNameTestBase extends FunctionalTestBase{

private final Randomizer random;

  public ProjectNameTestBase() {
    random = Randomizer.getInstance();
  }
}
```

Then calling `random.getRandomIntAsString();` will return a random positive Integer as a String between 0 and 999999999.

#### FileUtil
A basic implementation of the `FileUtil` is demonstrated in `nl.overheid.projectname.base` > `ExampleFunctionalityAction`. With this feature relying on `Classloader` to work with resources, this also means that file operations are confined to (file paths in) root folder `projectname/target`. Content from files is loaded from either `target/classes` or `target/test-classes` depending on whether calls are made from `src/main/java` or `src/test/java`, respectively.
For example: 
A file under `target/test-classes/example/sh/samplescript.bat` should get the relative file path `example/sh/samplescript.bat` and can be found in Eclipse in `src/test/resources/example/sh/samplescript.bat`.

To access the methods in this helper class first declare and instantiate the library as follows:
```java
public class ExampleFunctionalityAction {

private final FileUtil file;

  public ExampleFunctionalityAction() {
    file = new FileUtil();
  }
}
```
This helper class contains generic methods for using files as resources in your project.

#### ShellUtil
Example features are provided in `src/test/resources` > `ExampleShell` which demonstrate how `ShellUtil` can be used with both Linux and Windows.
Step definitions are available in the `Selenium Test Framework` in `nl.overheid.stf.shared.stepdef` > `ShellStepDef`. These steps contain all current methods available in the `ShellUtil` and cover the most likeliest of shell interactions.
The shell features can be executed from the `projectNameShellSuiteTest.java` runner class. Just make sure to set the appropriate tag for @linux or @windows beforehand.

### BrowserStack integration
All information for using BrowserStack to run your test scenarios is available [here](doc/browserstack.md).

### Jenkins integration
All information for using Jenkins to run your test scenarios is available [here](doc/jenkins.md).
All information for using Docker (Selenium Standalone) within Jenkins is available [here](doc/docker.md).

## Windows Environment Settings
Working in a Windows environment like the SCC-Campus VDI some additional settings might be required. The document that describes these settings and steps is available [here](doc/windows.md).
