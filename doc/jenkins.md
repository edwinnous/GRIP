# Jenkins Integration

## Plugins
The following plugins are needed to run in Jenkins:
- Cucumber reports
- Environment Injector Plugin

## Dependencies
The following dependencies are needed on the machine:
- Apache maven
- Java 1.11 or higher
- settings.xml with a server reference to where the SeleniumTestFramework package can be found, detailed info is given [here](maven.md).

## Job configuration
Job configuration details listed per type.

### Build environment
If you need to inject test user credentials in the Build environment:
- Select `Inject environment variables to the build process` and enter `Properties Content` with the following convention:
`admin.username = [name]`
- Select `Inject passwords to the build as environment variables` and enter `Job passwords` with the following convention:
`Name` = `admin.password` ; `Password` = `[password]`
- Select `Mask password parameters` to have the passwords masked during the run of the job.
Your code will now be able to get these credentials by getting them as environment variables in your test code.

### Run parameters
Set the run parameters of the build inside the `Goals and options`. A standard run parameter set will look like :
`test -Dtest.app.path=http://[test.url] -Dsettings.usebrowser=browserstack -Dconfig=[filepathTo][fileName].conf.json`

### BrowserStack
More information on running with BrowserStack can be found [here](browserstack.md).

### Cucumber reports
Set the field `File Include Pattern` to `**/*report.json`, this has to correspond with the value set inside the cucumber runner class under `plugin "json"` in your projects code.

## Docker
Information on running Selenium with Docker can be found [here](docker.md).
