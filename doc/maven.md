# Using GitHub Maven Package

## Maven Settings
The example `settings.xml` below contains the minimum required elements to enable downloading the Selenium Test Framework maven packages to your project. Simply insert the user or organization name and personal access token where needed. It might be needed to update the project again after setting up this file. This file is located under `/Users/UserName/.m2` in windows or `/home/UserName/.m2` in linux. The sections for `server` and `repository` can be copied to your local file.
If this file or folder does not exist you could start up Eclipse and import the project first or make a new `.m2` filemap with the `settings.xml` file as given below.

```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <pluginGroups>
  </pluginGroups>

  <proxies>
  </proxies>

  <servers>
    <!--
     | For GitHub use:
     | Replace [ GITHUB USER NAME] with your GitHub account name.
     | Replace [ PERSONAL ACCESS TOKEN ] with your own PAT.
    -->
    <server>
      <id>github</id>
      <username>[ GITHUB USER NAME]</username>
      <password>[ PERSONAL ACCESS TOKEN ]</password>
    </server>
    <!--
     | For GitLab use:
     | Do NOT replace the name for Private-Token, this is specified by GitLab.
     | Replace [ PERSONAL ACCESS TOKEN ] with your own PAT.
    -->
    <server>
      <id>gitlab</id>
      <configuration>
        <httpHeaders>
          <property>
              <name>Private-Token</name>
              <value>[ GITLAB PERSONAL ACCESS TOKEN ]</value>
          </property>
        </httpHeaders>
      </configuration>
    </server>
  </servers>

  <mirrors>
  </mirrors>

  <profiles>
    <!--
     | For GitHub use the following profile.
    -->
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/RIVM-syso/SeleniumTestFramework</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
    <!--
     | For GitLab use the following profile.
    -->
  <profile>
      <id>gitlab</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>gitlab</id>
          <url>https://gitlab.int.ssc-campus.nl/api/v4/projects/913/packages/maven</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

    <!--
     | Set your active profile by id name.
    -->
  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

</settings>
```
