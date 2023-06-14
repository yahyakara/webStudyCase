Feature: Boutique Links and Loading Durations
  As a user
  I want to collect boutique information and measure the loading durations of boutique links

  Scenario: Record boutique links and loading durations
    # Given I am on the home page
    Given I am on electronic boutique page
    And I do not want to see the HVTB pop-up
    And I do not want to see the Optanon alert box closed
    When I collect all boutique information
    Then  I measure and report the status of boutique links and their images, and I want the results in an Excel file named "boutique.xlsx"