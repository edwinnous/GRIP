#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@nightly @ui
Feature: Example feature
  This example shows how to build up the Cucumber scenarios

  Background: 
    Given Atabase is open

  @test
  Scenario: Go to RIVM search page
    #Given the user is logged in as 'ADMIN'
    When the user enters the username 'edwin.nous@rivm.nl'
    And the user enters the password 'bYjMMZLuqqzRsG3'
    And the user clicks sign in
    #Then the status message contains 'Er is geen zoekopdracht ingevoerd, voer een zoekopdracht in.'
    Then the page title is 'Dashboard'
     #Then the user adds a new 'Landingspagina'
    #Given I want to write a step with precondition
      #And some other precondition
     #When I complete action
      #And some other action
      #And yet another action
     #Then I validate the outcomes
      #And check more outcomes
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
     #When I check for the <value> in step
     #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
