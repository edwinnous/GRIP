# BrowserStack Integration

## Plugins
The following plugins are needed to run BrowserStack in Jenkins:
- [BrowserStack Jenkins plugin](https://plugins.jenkins.io/browserstack-integration/)

## BrowserStack credentials
There are three ways to add your BrowserStack credentials to run with your project.
1. Jenkins credentials:
BrowserStack credentials can be added from the `Build environment` in the Jenkins job by selecting `BrowserStack`. This is the preferred approach to add BrowserStack credentials in Jenkins because it masks the access key in the console log output. The access key is however still visible as an Environment variable inside the jobs `Environment Variable` tab.
2. Run parameter:
Credentials can also be passed as run parameters using the keywords `browserstack.username` and `browserstack.access.key`. This also works for test runs on a local machine.
3. Config file:
You can fill the BrowserStack config.json keywords `user` and `key`. These files also can be merged in your project and for local testing it is advised to place these files in a folder that is not included in commits.

## BrowserStack config file
One or more config files are needed to run testautomation in BrowserStack. The name convention of the file is `[configName].conf.json`. A standard config file will look something like this:
```json
{
  "server": "hub-cloud.browserstack.com",
  "user": "",
  "key": "",

  "capabilities": {
    "projectName": "SeleniumTestTemplate",
    "buildName": "ExampleConfig",
    "sessionName": "ExampleTest",
    "debug": true,
    "browserstack.local": true
  },

  "environments": [{
    "browserName": "Chrome"
  }]
}
```
Set the capability `browserstack.local` to `true` if the test environment can only be reached via the local machine.
To use the correct config file for a testrun the run parameter has to be specified as follows:
`-Dconfig=[filepath][configName].conf.json`

## Running multiple instances on the same server
If you run more than one test project at the same time on the same environment you need to add a `localIdentifier` tag to your scenario. You can do this by adding this to the capabilities in your config file, f.e.:
```json
"capabilities" {
  "localIdentiefier": "ExampleID"
}
```

## Running BrowserStack Local App
If you have your BrowserStack Local App running, this might interfere if you start a testrun on your local machine. Make sure that your BrowserStack Local App is completely closed.
More information on BrowserStack capabilities and environment variables can be found [here](https://www.browserstack.com/docs/automate/selenium/getting-started/java).
