Feature: Login page Website Swag Labs

  @Login @Positive
  Scenario: Success Login
    Given Halaman Login Swag Labs
    When Input Username
    And Input Password
    And Click login button
    Then User in on dashboard page

  @Login @Negative
  Scenario: Failed Login
    Given Halaman Login Swag Labs
    When Input Username
    And Input Invalid Password
    And Click login button
    Then User get error message

  @Login @TDD
  Scenario Outline: User Login to Swag Labs
    Given Halaman Login Swag Labs
    When I input <username> as username
    And I input <password> as password
    And Click login button
    Then I verify <status> login result

    Examples:
      | username  | password | status |
      | standard_user  | secret_sauce | success |
      | standard_user  | secret_sauces | failed |
