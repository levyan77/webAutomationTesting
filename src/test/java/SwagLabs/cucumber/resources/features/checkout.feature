Feature: Checkout

  @Test @Positive @Checkout
  Scenario: User "standart_user" doing checkout of the chosen product and can access checkout form page
    Given user login with (standart_user) valid credentials
    Then user choose the product in the Inventory page
    And user click (add to cart) button in the product page
    Then user click cart icon in the top right of the Product page
    And user click checkout  button
    Then user redirected to checkout form page

  @Test @Negative @Checkout
  Scenario: User "standart_user" doing checkout of the chosen product in cart, but there is no chosen product
    Given user login with (standart_user) valid credentials
    Then user click cart icon in the top right of the Product page
    And user click checkout  button
    Then user get error message