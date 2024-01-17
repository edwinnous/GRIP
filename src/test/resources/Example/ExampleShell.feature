@shell @linux
Feature: Linux Shell Commands Example feature
  This example demonstrates both an implicit and explicit way to execute shell commands from Cucumber scenarios.
  The command(s) can either be specified explicitly with a step argument or by directly executing a shell script.
  Please note that every command is started in a separate process from the root of the project folder.

  Scenario: Linux directory command with explicit exit value check and one-line output assertion including newline characters
    Given a shell command is executed: 'ls'
     Then the shell exit status value is: 0
      And the shell output is:
      | doc\npom.xml\nREADME.md\nsrc\ntarget\n |

  Scenario: Linux directory command with implicit exit value check and multi-line output assertion reusing step with DocString notation
    Given a shell command 'ls' is executed successfully
     Then the shell output is:
      """
      doc
      pom.xml
      README.md
      src
      target

      """

  Scenario: Linux redirect output of directory command to file and assert write permission using DocString
    Given a shell command 'ls > dir.txt' is executed successfully
     Then a shell command 'cat dir.txt' is executed successfully with output:
      """
      dir.txt
      doc
      pom.xml
      README.md
      src
      target

      """

  Scenario: Linux remove file command to assert delete permission with one-liner
    Given a shell command 'cat dir.txt' is executed successfully
     When a shell command 'rm dir.txt' is executed successfully
     Then a shell command 'ls' is executed successfully with output:
      | doc\npom.xml\nREADME.md\nsrc\ntarget\n |

  Scenario: Linux directory command with partial output assertion
    Given a shell command 'ls' is executed successfully
     Then the shell output matches lines:
      | doc       |
      | pom.xml   |
      | README.md |
      | src       |
      | target    |

  Scenario: Linux multi-line command and log-dump for reference
    Given a shell multi-line command is executed:
      """
      pwd
      ls -la > dir.txt
      cat dir.txt
      rm dir.txt
      """
      And the shell execution logs are dumped to the console

  Scenario: Linux shell script made executable and executed as a single process
    Given a shell command 'chmod +x target/test-classes/Example/sh/samplescript.sh' is executed successfully
     When a shell script 'samplescript.sh' is executed
     Then the shell exit status value is: 0

  Scenario: Linux shell script contents executed line by line
    Given a shell script 'Example/sh/samplescript.sh' is executed line by line
     Then the shell exit status value is: 0

  Scenario: Linux grep command with error status code and multi-line error assertion
    Given a shell command is executed: 'grep'
     When the shell exit status value is: 2
     Then the shell output contains lines:
      | Usage: grep [OPTION]... PATTERNS [FILE]... |
      | Try 'grep --help' for more information.    |
