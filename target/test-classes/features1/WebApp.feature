
@ui @healthtest @Ajio
Feature: Ajio WebApp Testcase
  
  Scenario: user is able to open browser, navigate to url and search for product
    Given user opened browser
    And url navigate to web url
    When user searched for product "hoodies"
    Then output displayed for product
    Then user closed the browser

  