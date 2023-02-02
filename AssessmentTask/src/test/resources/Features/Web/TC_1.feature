Feature: Convert USD dollar to Indian Rupee

  Scenario Outline: Convert USD dollar to Indian rupee and display it
    Given I should be on xe page
    When I should be able to enter the "<amount>" in Amount field
    And I should be able to select the "<country1>" and "<country2>" from the drop down
    Then Amount should be converted and get the converted money

    Examples: 
      | amount | country1 | country2 |
      |      5 | USD      | INR      |
