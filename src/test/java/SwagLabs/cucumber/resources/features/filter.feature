Feature: Filter Products

  @Filter @Positive
  Scenario: User "standard_user" filters products (price from lowest to highest) on the main page
    Given the user logs in with the credentials (standard_user)
    When the user clicks on the filter icon in the top right corner
    And the user selects the Price (low to high) filter option
    Then all products are filtered correctly

  @Filter @Negative
  Scenario: User "problem_user" cannot filter products on the main page
    Given the user logs in with the credentials (problem_user)
    When the user clicks on the filter icon in the top right corner
    And the user selects the Price (low to high) filter option
    Then all products are not filtered correctly