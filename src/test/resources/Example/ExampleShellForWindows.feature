@shell @windows
Feature: Windows Shell Commands Example feature
  This example demonstrates both an implicit and explicit way to execute shell commands from Cucumber scenarios.
  The command(s) can either be specified explicitly with a step argument or by directly executing a shell script.
  Please note that every command is started in a separate process from the root of the project folder.

  Scenario: Windows directory command with explicit exit value check and one-line output assertion including newline characters
    Given a shell command is executed: 'dir /b'
     Then the shell exit status value is: 0
      And the shell output is:
      | .classpath\n.gitignore\n.project\n.settings\nbin\ndoc\npom.xml\nREADME.md\nsrc\ntarget\n |

  Scenario: Windows directory command with implicit exit value check and multi-line output assertion reusing step with DocString notation
    Given a shell command 'dir /b' is executed successfully
     Then the shell output is:
      """
      .classpath
      .gitignore
      .project
      .settings
      bin
      doc
      pom.xml
      README.md
      src
      target

      """

  Scenario: Windows redirect output of directory command to file and assert write permission using DocString
    Given a shell command 'dir /b > dir.txt' is executed successfully
     Then a shell command 'type dir.txt' is executed successfully with output:
      """
      .classpath
      .gitignore
      .project
      .settings
      bin
      dir.txt
      doc
      pom.xml
      README.md
      src
      target

      """

  Scenario: Windows remove file command to assert delete permission with one-liner
    Given a shell command 'type dir.txt' is executed successfully
     When a shell command 'del /q dir.txt' is executed successfully
     Then a shell command 'dir /b' is executed successfully with output:
      | .classpath\n.gitignore\n.project\n.settings\nbin\ndoc\npom.xml\nREADME.md\nsrc\ntarget\n |

  Scenario: Windows directory command with partial output assertion
    Given a shell command 'dir /b' is executed successfully
     Then the shell output matches lines:
      | .classpath |
      | .gitignore |
      | .project   |
      | .settings  |
      | bin        |
      | doc        |
      | pom.xml    |
      | README.md  |
      | src        |
      | target     |

  Scenario: Windows multi-line command and log-dump for reference
    Given a shell multi-line command is executed:
      """
      echo %cd%
      dir /b > dir.txt
      type dir.txt
      """
      And the shell execution logs are dumped to the console
      And a shell command 'del /q dir.txt' is executed successfully

  Scenario: Windows bat script made executable and executed as a single process
    When a shell script 'Example/sh/samplescript.bat' is executed
    Then the shell exit status value is: 1

  Scenario: Windows shell script contents executed line by line
    Given a shell script 'Example/sh/samplescript.bat' is executed line by line
     Then the shell exit status value is: 0

  Scenario: Windows findstr command with error status code and error assertion
    Given a shell command is executed: 'findstr'
     When the shell exit status value is: 2
     Then the shell output contains lines:
      | FINDSTR: Bad command line |

  Scenario: Windows PowerShell command
    Given a shell command 'powershell Get-Help' is executed successfully
     Then the shell output contains lines:
      | TOPIC             |
      | SHORT DESCRIPTION |
