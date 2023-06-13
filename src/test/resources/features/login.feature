Feature: Login to the website
  As a user, I want to log in to my Trendyol account.

  Background:
    Given I go to login page

  Scenario: Log in with valid username and password
    Then I should be on the login page
    Given I log in to the app with valid credentials - data index 0
    And I should see my full name in the navigation bar

  Scenario: User should be able to view the password by clicking on the eye icon
    Given I go to login page
    When I fill the login form "test_email@gmail.com" and "123456"
    When I show the hided password
    Then I should be able to view the password
    When I hide the password
    And I shouldn't be able to view the password

  Scenario: Navigation from header login component
    Given I navigate to the login page by clicking it from the header menu
    Then I should be on the login page

  Scenario Outline: Login fail - Possible Combinations
    When I fill the login form "<E-Mail>" and "<Password>"
    And I click the Sign in button
    Then I should see the login failed error "<ErrorMessage>"

    Examples:
      | E-Mail                   | Password | ErrorMessage                               |
      | wrongemail0020@gmail.com | 123456   | E-posta adresiniz ve/veya şifreniz hatalı. |
      | mail0020@gmail.com       |          | Lütfen şifrenizi giriniz.                  |
      |                          | 123456   | Lütfen geçerli bir e-posta adresi giriniz. |
      |                          |          | Lütfen geçerli bir e-posta adresi giriniz. |
