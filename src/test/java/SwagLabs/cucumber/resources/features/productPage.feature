Feature: Product Page
  @ProductPage @Positive
  Scenario: User "standard_user" accesses the product page (Sauce Labs Backpack)
    Given the user logs in with credentials (standard_user)
    Then the user clicks on the image or text of the product (Sauce Labs Backpack) on the main page
    Then the user lands on the product page (Sauce Labs Backpack)
  @ProductPage @Negative
  Scenario: User "problem_user" is not directed to the correct product page
    Given the user logs in with credentials (problem_user)
    Then the user clicks on the image or text of the product (Sauce Labs Backpack) on the main page
    Then the user lands on a product page other than (Sauce Labs Backpack)
