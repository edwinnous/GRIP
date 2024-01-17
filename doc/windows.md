# Windows settings
The settings and steps provided in this document are additional steps that might be needed to work with the SCC-Campus Windows VDI environment, the framework can run without them. 

## Installing Java
Download the latest JDK from [Oracle](https://www.oracle.com/nl/java/technologies/javase-downloads.html) and install with default settings
Set the Java environment variables for Windows:
1. Start menu > Environment variables > Environment Variables
2. New `System variable`:
  - Variable name: `JAVA_<version_number>_HOME`
  - Variable value: `<filepath>\bin`
3. Add variable to system variable `Path`:
  - `Path` > `Edit`
  - New: `%JAVA_<version_number>_HOME%`

## Installing Maven
Download the latest Maven source from [Apache](https://maven.apache.org/download.cgi) and extract to your DevTools folder.
Set the Maven environment variables for Windows:
1. Start menu > Environment variables > Environment Variables
2. New `System variable`:
  - Variable name: `MVN_HOME`
  - Variable value: `<filepath>\bin`
3. Add variable to system variable `Path`:
  - `Path` > `Edit`
  - New: `%MVN_HOME%`

## Setting up SSH key
This setup is based on the use of SourceTree as your Git client
Download and install the required software:
- `Git for Windows` from [GitforWindows](https://gitforwindows.org/)
- `SourceTree` from [Atlassian](https://www.sourcetreeapp.com/)

### Generate a SSH key
1. Open `CMD`
2. To generate the key type `ssh-keygen -t rsa -b 4096 -C "test@example.com"` and press `Enter`
3. Press `Enter` to save to the default location `~/.ssh` with the default name `id_rsa` or type in a new location and name like `C:\Users\<name>/.ssh/new_key_name`
4. Enter a passphrase or leave empty and press `Enter`
5. Open `explorer` and move the keys to your `.ssh` folder on the `N` disk, make a new folder if this folder does not exist.

### Save private key via PuTTY
Open PuTTY via Sourcetree, do not open the stand-alone client of PuTTY.
1. Open `SourceTree` > `Tools` > `Create or Import SSH Keys`
2. Import key `Conversions` > `Import key` > `<id_rsa>`
3. Click on `Save private key` and save as `<id_rsa>.ppk'` in the `~/.ssh` folder on the `N` disk, do not forget to type out the `.ppk` in the filename.

### Add public key to Github
1. Open `Git Bash`
2. To copy the key to the clipboard type `clip < ~/.ssh/<id_rsa>.pub`
3. Add the key to Github: `Github.com` > `Settings` > `SSH key` > `New SSH key`
4. Give the key a name, paste the key and click `Add SSH key` 

### Add SSH key to Sourcetree
1. Add your private key to the SSH Agent `SourceTree` > `Tools` > `Launch SSH Agent`
2. In taskbar `PuTTY` > `Add key`
3. Add private key `<id_rsa>.ppk` from the `.ssh` filemap location on the `N` disk
