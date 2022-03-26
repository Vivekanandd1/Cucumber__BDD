

package FlipkartHealtCheck.stepdefs;

import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import FlipkartHealtCheck.core.WebDriverFactory;
import FlipkartHealtCheck.pageobj.BagWishListObject;
import FlipkartHealtCheck.pageobj.BrandSelctionPage;
import FlipkartHealtCheck.pageobj.CmnPageObjects;
import FlipkartHealtCheck.pageobj.HomePageObjects;
import FlipkartHealtCheck.pageobj.ProductDescriptionPageObjects;
import FlipkartHealtCheck.pageobj.SearchPageObjects;
import FlipkartHealtCheck.pageobj.SignInPageObjects;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class stepdefs {
	 //***********************************************************************
    //***********************Logger Init*************************************
    //***********************************************************************
	private static final Logger log = LogManager.getLogger(stepdefs.class);
	//***********************************************************************
    //***********************Declaration*************************************
    //***********************************************************************
	 WebDriver driver;
	 String url = "https://www.myntra.com/";
	 int implcit_wait_time =40;
	 Scenario scn;
	    //***********************************************************************
	    //***********************Page Object Model Declaration*******************
	    //***********************************************************************
	    // Declare Page Object Model (note that we are not initilizing them here)
	    // Before we could init them we need to have driver initialized
	    // We Will init these objects in @Before Set Up method only after Driver object in set
	   CmnPageObjects cmnPageObjects;
	    HomePageObjects homePageObjects;
	    SignInPageObjects signInPageObjects;
	    SearchPageObjects searchPageObjects;
	    ProductDescriptionPageObjects productDescriptionPageObjects;
	    BagWishListObject bagWishListObject;
	    BrandSelctionPage brandSelctionPage;
	    
	    // make sure to use this before import io.cucumber.java.Before;
	    // Use @Before to execute steps to be executed before each scnerio
	    // one example can be to invoke the browser
	    //Scenario(see below method arg type) is a Interface, given by Cucumber;
	    //This object is 'Injected' at run time and can be used for logging, screen shot attachement to reports
	    //Other than that it also carries steps and scenario pass, fail status(more on this later)
	 
	 @Before
	  public void setUp(Scenario scn) throws Throwable{
		 this.scn = scn; 	
		//Get the browser name by default it is chrome
	        String browserName = WebDriverFactory.getBrowserName();
	        driver = WebDriverFactory.getWebDriverForBrowser(browserName);
	        log.info("Browser invoked.");
	        
	      //Init Page Object Model Objects
	        cmnPageObjects = new FlipkartHealtCheck.pageobj.CmnPageObjects(driver);
	        homePageObjects = new FlipkartHealtCheck.pageobj.HomePageObjects(driver);
	        signInPageObjects = new FlipkartHealtCheck.pageobj.SignInPageObjects(driver);
	        searchPageObjects = new FlipkartHealtCheck.pageobj.SearchPageObjects(driver);
	        productDescriptionPageObjects = new FlipkartHealtCheck.pageobj.ProductDescriptionPageObjects(driver);
	        bagWishListObject = new FlipkartHealtCheck.pageobj.BagWishListObject(driver);
	        brandSelctionPage =new FlipkartHealtCheck.pageobj.BrandSelctionPage(driver);
	    }
	 
    @After(order=1)
    public void cleanup()
    {
    	WebDriverFactory.quitDriver();
    	log.info("browser closed");
    }
    @After(order=2) // this will execute first, higher the number, sooner it executes
    public void takeScreenShot(Scenario s) {
      if (s.isFailed()) {
          TakesScreenshot scrnShot = (TakesScreenshot)driver;
          byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
          scn.attach(data, "image/png","Failed Step Name: " + s.getName());
      }else{
          scn.log("Test case is passed, no screen shot captured");
      }
    }
	
//	@Given("User opened browser")
//	public void user_opened_browser() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(implcit_wait_time,TimeUnit.SECONDS);
//		
//	}


	//Some other steps were also undefined:

	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
	    WebDriverFactory.navigateToTheUrl(url);
	    scn.log("user navigated to URL " + url);
	    String exepected = "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
	    cmnPageObjects.validatePageTitleMatch(exepected);
	}
	@When("User Search for product {string}")
	public void user_search_for_product(String ProductName) {
		cmnPageObjects.SetSearchTextBox(ProductName);
        cmnPageObjects.ClickOnSearchButton();
        scn.log("Product Searched: " + ProductName);
	}
	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() {
		searchPageObjects.ValidateProductSearchIsSuccessfull();
	}

//    @Then("user closed browser")
//    public void user_closed_browser()
//    {
//    	driver.quit();
//    }
    
    @When("user clicked for Product")
    public void user_clicked_for_product() {
    	searchPageObjects.ClickOnTheProductLink(7);//7 here means indice of product in product list
    	 }


   
    @Then("prouct description displayed on tab")
    public void prouct_description_displayed_on_tab() {
    	 //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
    Set<String> handles = driver.getWindowHandles();// get all the open windows
    scn.log("List of windows found: "+handles.size());
    scn.log("Windows handles: " + handles.toString());
    Iterator<String> it = handles.iterator();// get the iterator to iterate the elements in set
    String orignial = it.next();//gives parent window id
    String ProductDescription  = it.next();//gives child window id
    driver.switchTo().window(ProductDescription);// switches to new window
   
    driver.switchTo().window(ProductDescription); // switch to product Descp
    scn.log("Switched to the new window/tab");
    
    productDescriptionPageObjects.ValidateProductTileIsCorrectlyDisplayed();
    productDescriptionPageObjects.ValidateAddToCartButtonIsCorrectlyDisplayed();
    
    driver.switchTo().window(orignial);
    scn.log("Switched back to Original tab");
   
 
    }
    
    @When("user clicked on Bag")
    public void user_clicked_on_bag() {
    	bagWishListObject.ClickOnBag();
    	scn.log("user clicked on bag");
        
    }


    //Some other steps were also undefined:

    @Then("product added from wishlist")
    public void product_added_from_wishlist() {
    	bagWishListObject.ClickOnWishlist();
    	scn.log("product added from wishlist");
        
    }
    @When("user clicked on Brand tag")
    public void user_clicked_on_brand_tag() {
    	brandSelctionPage.BrandSelect();
    	scn.log("user clicked on desired brand");
        
    }

    @When("User filters gender")
    public void user_filters_gender() {
        brandSelctionPage.GenFilter();
        scn.log("user filtered gender");
    }
    @Then("user selects choice from category")
    public void user_selects_choice_from_category() {
        brandSelctionPage.CategorySelection();
        scn.log("user selects category for product");
    }

 
}
