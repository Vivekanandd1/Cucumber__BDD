@ui @healtcheck @Myntra
Feature: E-commerce Project Web Site Health Check

Background: Navigation to the URL
  Given User navigated to the home application url

  Scenario Outline: User is able to Open the browser, navigate to the URL and Search for Product
    #Given User opened browser
   # And User navigated to the home application url
    When User Search for product "<product_name>"
    Then Search Result page is displayed
   Examples:
    |product_name|
    | Hoodies    |
    | Jeans      |
    | Shirt      |
    |Joggers     |

  @2nd @Product
	  Scenario: User is able to Open the browser, navigate to the URL and Search for Product as well as he can add product to cart
   #Given User opened browser
   #Gherkins
   # And User navigated to the home application url
    And User Search for product "Tshirt"
    When user clicked for Product
    Then prouct description displayed on tab
   #Then user closed browser
   
   @3rd @ProductBag
   Scenario: user is able to open browser and navigate to url after that it will add product from wishlist
  #  And User navigated to the home application url
    When user clicked on Bag
    Then product added from wishlist
   
   @4th @BrandChoice @ProductCatLog
   Scenario: User is able to open browser and navigate to url after that he will filter brand and navigate for selcetion
    # And User navigated to the home application url
    When user clicked on Brand tag
    And User filters gender
    Then user selects choice from category
   
