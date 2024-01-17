# Docker Integration

## Introduction
Docker integration support is currently based on using an existing working Jenkins server with a standalone SeleniumHQ image. When using the `io.fabric8` plugin the specified Selenium image will be downloaded automatically.

## Dependencies
The following dependencies are needed on the machine:
- Docker
- Selenium standalone docker image, releases can be found [here](https://github.com/SeleniumHQ/docker-selenium/releases)
The following plugin is needed in the project POM:
- io.fabric8 v0.41.0, user manual can be found [here](http://dmp.fabric8.io/)

## Plugin configuration
To run Selenium with docker the `pom.xml` needs a plugin configuration like the following:
```xml
      <plugin>
        <groupId>io.fabric8</groupId>
        <artifactId>docker-maven-plugin</artifactId>
        <version>${docker.version}</version>
        <configuration>
          <images>
            <image>
              <!-- The name of the image needs to correspond with the name from the publicly available releases for standalone docker images. -->
              <name>selenium/standalone-${settings.usebrowser}:${docker.selenium.version}-${docker.build.version}</name>
              <alias>${docker.container.name}</alias>
              <run>
                <privileged>${docker.privileged}</privileged>
                <ports>
                  <!-- Port 4444 is the standard port for the WebDriver. -->
                  <port>${docker.selenium.hub.port}:4444</port>
                  <!-- Port 5900 is the standard port for the VNC viewer. -->
                  <port>${docker.selenium.vnc.port}:5900</port>
                  <!-- Port 7900 is the standard port for the in-browser viewer. -->
                  <port>${docker.selenium.novnc.port}:7900</port>
                </ports>
                <env>
                  <!-- A large number of environment variables can be set, for instance the screen size. -->
                  <SE_SCREEN_WIDTH>1936</SE_SCREEN_WIDTH>
                  <SE_SCREEN_HEIGHT>1056</SE_SCREEN_HEIGHT>
                  <!-- The maximum number of concurrent sessions, only applicable if parallel testing is supported. -->
                  <SE_NODE_MAX_SESSIONS>2</SE_NODE_MAX_SESSIONS>
                </env>
                <!-- The minimum size is 524288000 bytes, the recommended minimum is however 2GB in bytes. -->
                <shmSize>2147483648</shmSize>
                <wait>
                <!-- Setting a wait makes sure that the docker image is running correctly before starting your test scenarios. -->
                  <http>
                    <url>http://localhost:${docker.selenium.hub.port}</url>
                  </http>
                  <!-- Timeout to stop polling unresponsive URL after 15 seconds, usually ready in ~4 seconds -->
                  <time>15000</time>
                </wait>
              </run>
            </image>
          </images>
        </configuration>
      </plugin>
```
### Memory size
The minimum recommended image size is currently 2GB or `2147483648` in bytes. Less could be used, but is unsupported. The absolute mimimum to start the docker container is 500MB or `524288000` bytes. Make sure the jenkins server has enough room for the images and containers as the current images are already 1.3GB in size.

### Browser Support
The standalone images for `Chrome`, `FireFox` and `Edge` have been tested to work with our framework. The selenium version chosen is just part of the release name and has no substantive implication for the actual Selenium version that is being used to run the WebDriver. 

### Browser Version
Instead of specifying the browser version, as is done with the local WebDriver with `settings.usebrowser.version`, the corresponding browser version is based on the image release date. For example to test on `Chrome 109` the image for `4.8.0` with date `20230202`, `20230131` or `20230123` can be used. Running an image should work for all releases for Selenium `^4.0.0` as they are backwards compatible.
`Disclaimer:
It is possible that a newer version than the currently used Selenium version for this framework is not backwards compatible. Using headless for Chrome will only work for Chrome version 109 and newer.`

### Image name
The name of the image should represent the full name of the released Selenium image, f.e.: `selenium/standalone-chrome:4.8.0-20230202`.
By using the following text in the plugin, the browser-type and Selenium version can be set inside the Jenkins job:
`<name>selenium/standalone-${settings.usebrowser}:${docker.selenium.version}-${docker.build.version}</name>`

## Ports and DNS
At the moment of writing Jenkins servers supplied by RIVM need to have specified which ports are going to be used and if traffic is in/outbound.

## Jenkins Job Specifications
To use Docker to run with the remote WebDriver you need to start and stop a container for the specified image release. If the image name and release date are not matched correctly, the run will result in an error. Some extra steps in your Jenkins Job are required to start, stop and clean up the container. In these steps some settings are specified as run parameter. This is not obligatory, but does prevent you from having to commit and merge a new `pom.xml` everytime you want to change f.e. the selenium image release.

### Pre-Build
In your Jenkins job add an `Execute shell` as Pre-Build step. For the example used in this project the Shell command would be the following:
`mvn docker:start -Dsettings.usebrowser=chrome -Ddocker.selenium.version=4.8.0 -Ddocker.build.version=20230202`
Other commands could also be specified here, as long as they correspond with a setting for the plugin in the `pom.xml`.

### Build
In the Build step `Goals and options` the following run parameters are needed, these are additional to your already specified existing run parameters:
`-Dselenium.server.url=http://localhost:4444 -Dsettings.usebrowser=chrome`
Where the browsername has to match the specified Selenium image browsername and the `selenium.server.url` port has to match the specified `docker.selenium.hub.port` as set in the `pom.xml`.

### Post-Build
In your Jenkins job add an `Execute shell` as Post-Build step. For the example used in this project the Shell command would be the following:
`mvn docker:stop -Dsettings.usebrowser=chrome -Ddocker.selenium.version=4.8.0 -Ddocker.build.version=20230202`

## Interactive Session
There are two main ways to interact with a running container:
1. Using a VNC viewer like [RealVNC](https://www.realvnc.com/en/connect/download/viewer/), which uses port 5900 in this example.
2. Using a Webbrowser, which uses port 7900 in this example.
The password for the container is `secret`.

## Error Guessing
If you get errors when trying to run via Docker you should check the following:
* Is the Image name written correctly according to the Selenium release.
* Is the correct browser set for all three steps.
* If the container starts with a browser, but cannot connect to the networks, then most likely the firewall or DNS is incorrectly set on the Jenkins server.
* Is there currently a container still running; only one container can run on the same port and only the job that started the container can connect to it. You need to manually stop the running container.
* Does you Jenkins server have enough memory to download a new image or start a container.
* If your job does not update the newly set image version, but keeps using an old one, a solution can be to check `Delete workspace before build starts` in the `Build environment` step.
* Using a clean install can sometimes solve issues. This can be done by setting a new command in the Pre-Build Shell script on line 1, that sais: `mvn clean install -DskipTests`.
* If all else fail you could clean up all existing containers (and images) and restart Docker and/or Jenkins.
