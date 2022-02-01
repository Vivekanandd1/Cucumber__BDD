package FlipkartHealtCheck.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FlipkartHealtCheck.core.WebDriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

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
	    FlipkartHealtCheck.pageobj.CmnPageObjects cmnPageObjects;
	    FlipkartHealtCheck.pageobj.HomePageObjects homePageObjects;
	    FlipkartHealtCheck.pageobj.SignInPageObjects signInPageObjects;
	    FlipkartHealtCheck.pageobj.SearchPageObjects searchPageObjects;
	    FlipkartHealtCheck.pageobj.ProductDescriptionPageObjects productDescriptionPageObjects;
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
	    }
	 
    @After
    public void cleanup()
    {
    	WebDriverFactory.quitDriver();
    	log.info("browser closed");
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
 
}
