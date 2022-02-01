@ui @healtcheck @Myntra
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    #Given User opened browser
    And User navigated to the home application url
    When User Search for product "Sweatshirts"
    Then Search Result page is displayed
   #Then user closed browser

  @2nd @Product
	  Scenario: User is able to Open the browser, navigate to the URL and Search for Product as well as he can add product to cart
   #Given User opened browser
    And User navigated to the home application url
    And User Search for product "Tshirt"
    When user clicked for Product
    Then prouct description displayed on tab
   #Then user closed browser
