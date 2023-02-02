Feature: API Automation

	Background:
    Given Retrieve the Open Weather Map end point
	
  Scenario Outline: Get the value from the Open Weather Map using q and appid
    When Hit "GET" end point to fetch the data in Open Weather Map using q for "<q>" and "<appid>"
    Then Verify the status code is 200
    And Get the values of following fields "lat","lon","description","pressure","country" and "name"

    Examples: 
      | q         | appid                             |
      | London    | b1b15e88fa797225412429c1c50c122a1 |
      | London,uk | b1b15e88fa797225412429c1c50c122a1 |

  Scenario Outline: Get the value from the Open Weather Map using id and appid
    When Hit "GET" end point to fetch the data in Open Weather Map using id for "<id>" and "<appid>"
    Then Verify the status code is 200
    And Get the values of following fields "lat","lon","description","pressure","country" and "name"

    Examples: 
      | id      | appid                             |
      | 2172797 | b1b15e88fa797225412429c1c50c122a1 |

  Scenario Outline: Get the value from the Open Weather Map using lat, lon and appid
    When Hit "GET" end point to fetch the data in Open Weather Map for "<lat>", "<lon>" and "<appid>"
    Then Verify the status code is 200
    And Get the values of following fields "lat","lon","description","pressure","country" and "name"

    Examples: 
      | lat | lon | appid                             |
      |  35 | 139 | b1b15e88fa797225412429c1c50c122a1 |

  Scenario Outline: Get the value from the Open Weather Map using zip and appid
    When Hit "GET" end point to fetch the data in Open Weather Map using zip for "<zip>" and "<appid>"
    Then Verify the status code is 200
    And Get the values of following fields "lat","lon","description","pressure","country" and "name"

    Examples: 
      | zip      | appid                             |
      | 94040,us | b1b15e88fa797225412429c1c50c122a1 |
      
  Scenario Outline: Get the value from the Open Weather Map using zip and appid
    When Hit "GET" end point to fetch the data in Open Weather Map using zip for "<zip>" and "<appid>"
    Then Verify the status code is 200
    And Get the values of following fields "lat","lon","description","pressure","country" and "name"

    Examples: 
      | zip      | appid                             |
      | 94040,us | b1b15e88fa797225412429c1c50c122a1 |
