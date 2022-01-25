package AjioWebAppCheck.stepdefs1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class stepdefs1 {
	 WebDriver driver;
	 String url = "https://www.ajio.com/";
	 int implcitwait = 30;
			 
	
	@Given("user opened browser")
	public void user_opened_browser() {
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    
	}


	//Some other steps were also undefined:

	@Given("url navigate to web url")
	public void url_navigate_to_web_url() {
	    driver.get(url);
	    //driver.findElement(By.xpath(url))
	    String expect = "Ajio.com | Shop Online the Latest Fashion in apparels brands in India | Reliance Ajio.com";
	    Assert.assertEquals(expect, driver.getTitle());
	}
	@When("user searched for product {string}")
	public void user_searched_for_product(String Productname) {
	    WebElement searchbox = driver.findElement(By.xpath("//input[@placeholder='Search AJIO']"));
	    searchbox.sendKeys(Productname);
	    
	}
	@Then("output displayed for product")
	public void output_displayed_for_product() {
		driver.findElement(By.className("ic-search")).click();;
		String expect1  = "Best Offers on Hoodies upto 20-71% off - Limited period sale | AJIO";
	    Assert.assertEquals(expect1, driver.getTitle());
	    
	}
	@Then("user closed the browser")
	public void user_closed_the_browser() throws InterruptedException {
	    Thread.sleep(3000);
	    driver.quit();
	}


}
