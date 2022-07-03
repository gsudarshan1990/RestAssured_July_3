@jira
Feature: To validate the jira api requestes

  Background: 
    Given The user should add the baseuri
  @createIssue
  Scenario Outline: To validate the jira create issue api request
    Given To give the "<summary>" and "<description>" for the post request
    When To call the "createissue" api with "POST" http request method
    Then The status code should be 201
    And The response body self contains the name "swiggyy"

    Examples: 
      | summary            | description         |
      | Login not working3 | Cant able to login3 |
  @getIssue
  Scenario: To vaidate the jira get api request
    When To call the "getissue" api with "GET" http request method
    Then The status code should be 200

  @updateIssue
  Scenario Outline: To validate the jira update issue api request
    Given The user should update "<summary>" and "<description>" for the update issue
    When To call the "updateissue" api with "PUT" http request method
    Then The status code should be 204

    Examples: 
      | summary            | description         |
      | Login not working4 | Cant able to login4 |

  @deleteIssue
  Scenario: To vaidate the jira delete api request
    When To call the "deleteissue" api with "DELETE" http request method
    Then The status code should be 204
