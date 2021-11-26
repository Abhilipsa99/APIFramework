Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if place is being added successfully using AddPlace API
    Given Add Place payload with "<name>" "<language>" "<address>"
    When User calls the "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify that place_api created maps to "<name>" using "GetPlaceAPI"

    Examples:
    |name      | language | address |
    |Frontline House  | French  | World Cross Centre |
    |AA House  | Spanish  | 29, side layout, cohen 09 |

    @DeletePlace
    Scenario: Verify if place is deleted successfully using DeletePlace API
      Given DeletePlace Payload
      When User calls the "DeletePlaceAPI" with "Post" http request
      Then the API call is success with status code 200
      And "status" in response body is "OK"

