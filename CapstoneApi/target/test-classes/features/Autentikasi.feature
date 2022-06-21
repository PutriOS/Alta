@Auth
Feature: Login User Endpoint

  @Login
  Scenario Outline: POST - As a user i have to be able to login
    Given I set an endpoint for login
    When I request POST for login with "<email>" and "<password>"
    Then I validate the status code <status_code>
    And get tokens for other request
    And get refresh tokens for other request
    Examples:
      |email|password|status_code|
      |alsyadahmad@holyhos.co.id|password|200|

  @Logout
  Scenario: Logout Existing User
    Given I set logout endpoint
    When I request POST for logout
    Then I received HTTP response 200
    And I validate response message

  @RefreshToken
  Scenario: Refresh Token
    Given I set refresh token endpoint
    When I request POST for refresh token
    Then I received HTTP response 200
    And get token baru for other request