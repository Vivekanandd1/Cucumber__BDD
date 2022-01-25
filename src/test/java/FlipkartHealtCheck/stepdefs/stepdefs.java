package FlipkartHealtCheck.stepdefs;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class stepdefs {
	 WebDriver driver;
	 String url = "https://www.myntra.com/";
	 int implcit_wait_time =40;
	 
	
	@Given("User opened browser")
	public void user_opened_browser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(implcit_wait_time,TimeUnit.SECONDS);
		
	}


	//Some other steps were also undefined:

	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
	    driver.get(url);
	    String exepected = "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
	    String Actual = driver.getTitle();
	    System.out.println(Actual);
	    Assert.assertEquals("page title validation", exepected, Actual);
	}
	@When("User Search for product {string}")
	public void user_search_for_product(String ProductName) {
		
	    WebDriverWait webdriverwait = new WebDriverWait(driver, implcit_wait_time);
	    WebElement Elementsearchbox = webdriverwait.until(ExpectedConditions.elementToBeClickable(By.className("desktop-searchBar")));
	    Elementsearchbox.sendKeys(ProductName);
	    driver.findElement(By.className("desktop-submit")).click();	    
	}
	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() {
	    WebDriverWait webdriverwait1 = new WebDriverWait(driver, implcit_wait_time);
	    webdriverwait1.until(ExpectedConditions.titleIs("Sweatshirts - Get upto 80% off on Sweatshirts for Men & Women Online - Myntra"));
	    String lastExpect = "Sweatshirts - Get upto 80% off on Sweatshirts for Men & Women Online - Myntra";
	    Assert.assertEquals("page validation", lastExpect, driver.getTitle());
	}

    @After
    public void teardown()
    {
    	driver.quit();
    }
 
}
