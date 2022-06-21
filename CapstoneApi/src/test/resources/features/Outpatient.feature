#@Auth
#Feature: Login User Endpoint
#
#  @Login
#  Scenario Outline: POST - As a user i have to be able to login
#    Given I set an endpoint for login
#    When I request POST for login with "<email>" and "<password>"
#    Then I validate the status code <status_code>
#    And get token for other request
#    Examples:
#      |email|password|status_code|
#      |alsyadahmad@holyhos.co.id|password|200|