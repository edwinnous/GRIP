@api
Feature: RestAssured Example feature for the RIVMweb API
  This example demonstrates an approach to building Cucumber scenarios with the RestAssuredWrapper

  @get @no-auth
  Scenario: GET session token
    Given the user requests a session token
     Then the server responds with status code 200
      And the session token is set as header

  @get @no-auth
  Scenario: GET AUTHOR by id and test for status code 401 unauthorized
    When the user sends an unauthorized GET request at endpoint 'AUTHOR' querying value '1'
    Then the server responds with status code 401
     And the server response is received in less than 1500 milliseconds
     And the server response contains message:
      | message | No authentication credentials provided. |
