@ui @healtcheck @flipkart
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User opened browser
    And User navigated to the home application url
    When User Search for product "Sweatshirts"
    Then Search Result page is displayed