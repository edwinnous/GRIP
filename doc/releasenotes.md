# Release Notes

## Version 1.4.3
release date August 20, 2023

- Updated Selenium to version 4.12.0
- Updated Boni Garcia WebDriverManager to 5.5.3 to fix issues with ChromeDriver manager for Chrome version 115+

## Version 1.4.2
release date August 20, 2023

- Updated Selenium to version 4.11.0
- Updated Boni Garcia WebDriverManager to 5.4.1 to fix issues with ChromeDriver manager

## Version 1.4.1
release date April 24, 2023

- Updated Selenium to version 4.9.0.
- Removed unused import and static String.

## Version 1.4.0
release date February 16, 2023

- Selenium 4.8.0:
  - Updated Selenium to version 4.8.0.
  - Updated io.fabric8 to version 0.41.0
  - Removed `selenium_linux.properties` as this is no longer needed or supported.
- Docker:
  - Added documentation for running WebDriver via Docker in a Jenkins Server.
  - Currently supported and tested Selenium Docker images are for standalone only.
  - Tested browsers are Chrome, FireFox and Edge.
- DatabaseWrapper class:
  - Database connections are referred to as `nodes` and can be addressed by name from cucumber scenarios. One or more database nodes can be specified by using environment variables (preferred) and/or the `database.properties` file.
  - This is an unsupported wrapper created with limited testing but by no means battle hardened. Use at your own discretion!
  - Known issue: for this version every `.feature` file runing DB tests an `@api` tag is required in the header to prevent the framework from attempting to start a browser.
  - Added a mariadb JDBC connector dependency just because it was the quickest setup. Any database with an appropriate connector can be targeted flexibly using the connection string.
- Added a style guide for coding standards we want to maintain.

## Version 1.3.2
release date January 26, 2023

- Selenium 4.7.2:
  - Updated Selenium to version 4.7.2.
  - Updated Boni Garcia WebDriverManager to 5.3.2 to fix a conflicting version bug of Guave between our older version of WebDriverManager 5.0.3 and Selenium 4.1.0 and higher.
  - The update also comes with an updated version of the java.lang package to Lang3. Some projects might need to change some code that uses methods from Lang version 2 and older.
- Cookies for HTTP-request:
  - Added a new `getRequestSpec` method that sets Cookies after calling the basic `getRequestSpec` method.
  - Added three new request methods for `PATCH, POST and PUT` that also set the cookies.
- Added docker pom example.
- BrowserStack:
  - Removed preloaded browser version to be able to standard run on the latest version.
  - Removed an `assertFail` from catching an error which would have BrowserStack not start and now just log the error.

## Version 1.3.1
release date August 11, 2022

- File Util refactoring:
  - Class name changed to `FileUtil`.
  - Removed the split 'file path/folder' and 'file name' variable options to only support the `file path` variable as resource locator.
  - The `getFilePath()` method now uses the `ClassLoader` method.
  - Added methods to get the filename of a `File`
  - Added method `getResourceFileAsString` to return the content of a resource file as a string with joining glue code.
  - Added method `getResourceFilesFromFolder` to return a `File` array of all files from the specified folder.
  - Added method to get resource or file as `InputStream`.
  - Added methods to return a `BufferedReader` based on `String`, `File` or `InputStream` as given variable.
  - Removed the `getResourceFile` from the `RestAssuredWrapper` as it is no longer needed.
  - Removed the `getFilePath` from the `FunctionalTestBase` as it is no longer needed.
  - `FunctionalTestBase` methods `inputSetValueFromFile` and `uploadFile` no longer suppor a split folder path and file name variable and only use the `filePath` as a variable.
  - Changed the `CredentialManager` method `getCredentialValue` to now use the new method in the `FileManager`. This means that using the standard gitignore files under the root of the project in a  `/config` map is no longer supported for running local `test.users properties`.
  - More information about this util class can be found inside the class itself.
- Introducing `Shell.java` class:
  - The `Shell` class will serve as a convenient way of instantiating a shell process and executing commands on the host OS. Commands, regular output and errors are cached during the test session for further checks or review. Currently the class was tested on both windows and linux, mac should probably work like linux but untested as of yet.
  - Generic step definitions and an `ExampleShell.feature` and `ExampleShellForWindows.feature` are included to demonstrate how to work with shell commands in cucumber. A `samplescript.sh` and `samplescript.bat` are included to test running shell scripts as a command, however, even if the linux `executable` flag is set this doesn't translate to the version copied to the target build folder. Hence the extra `chmod +x` step in the example scenario.
 - For now the process execution time is limited by a timeout of 9 seconds, just to ensure nothing runs indefinitely.
 - The `Shell` class is accompanied by standard `step definitions` inside `shared/stepdef` > `ShellStepDef.java`.
 - More information about this class can be found inside the class itself and alongside the `step definitions`.
- All examples in the `STF` package have been moved to `src/test` and will no longer give conflicting `step definitions` when the same `step definition` is used in your own project.
- Added `scrollElementIntoView` method to the `FunctionalTestBase` and added this to the `waitForElementVisible` method.
- Added an `Assert.fail` to the `browserStackLocal` method to fail the testrun when a BrowserStack local app is already running on your machine or the port is already in use. Also added added more verbose logging to give a better explanation of why the testrun failed.
- Introduced new functional runner tag `@ui`. This tag will enable screenshots to be taken when a test fails.

## Version 1.3.0
release date June 2, 2022

- Updated current Java version to 11.
- Added support for API testing using `RestAssured`. The most common requests can now be sent in one line using the convenience methods provided by the `RestAssuredWrapper`. For specific cases a custom request can be build up with headers and parameters from scratch.
- Added a default set of step definitions to provide examples and a setup for instant API testing.
- Added options to send JSON data directly from the Cucumber feature files as well as referring to content stored in `.json` files.
- Added a new helper class called `FileManager` that contains generic methods using files as a resource:
  - Added `getFilePath` to get your local file path as String.
  - Added `getResourceAsStream` to get your file resource as a stream from the classloader.
  - Added `getFileAsStream` to get your file resource as a stream from a specified file path.
  - Added `getPropertyValueFromStream` to get a property value from a file resource stream.
  - Added `getPropertyValueFromEnvironment` to get a property value from an environment variable.

## Version 1.2.2
release date March 7, 2022

### Fixes
- Fixed the `waitForElementNotPresent` where the method would occasionally return a `noSuchElementException` because the `expectedConditions` was inverted. This issue is know by Selenium with inverting `expectedConditions`.
- Fixed `waitForElementInvisible` where the method would occasionally return a false positive, because the method searched for an `elementList` instead of a single specific element. This method now searches only for one element.
- Added `waitForElementsInvisible` to search for all elements to be invisible given by specified locator.

## Version 1.2.1
release date: February 1, 2022

- Added functionality to add specified language settings as driver capabilities/options:
  - This settings can be set using `settings.usebrowser.language`
  - The default is set to `nl-NL`
- Added a `waitForJQueryNotActive` method to wait for Javascript to load:
  - Added a `waitForLoading` method to implement this wait and log the time waiting.
  - Added `waitForLoading` to the `buttonClick` and `buttonClickJs` methods.
- Added a `getFilePath` method to get the local file path as a String.
- Added a `uploadFile` method as a generic file upload method.
- Added a `inputSetValueFromFile` method to send text from a file, line by line, to the specified element.
- Changed `inputSetValue` method to use the default Selenium `WebElement.clear()` functionality instead of our own custom code.
