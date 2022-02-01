package FlipkartHealtCheck.pageobj;

/***
 * Author: Vivekanand Deshmukh
 * Company: Myntra
 * Date: 1-2-2022
 * Description: Test Automation FW development
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CmnPageObjects {
	private static final Logger log = LogManager.getLogger(CmnPageObjects.class);
	WebDriver driver;

	private By search_text_box = By.xpath("//input[@class='desktop-searchBar']");
	private By search_button = By.xpath("//a[@class='desktop-submit']");
	private By nav_link_logo =  By.xpath("//a[@class='myntraweb-sprite desktop-logo sprites-headerLogo']");
	private By nav_link_cart =  By.xpath("//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']");
	private By nav_link_wishlist =  By.xpath("//span[@class='myntraweb-sprite desktop-iconWishlist sprites-headerWishlist']");
	private By nav_link_profile =  By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-headerUser']");

	
	public CmnPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void SetSearchTextBox(String text) {
		WebDriverWait webDriverWait = new WebDriverWait(driver,20);
		WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(search_text_box));
		elementSearchBox.sendKeys(text);
		log.info("Value entered in search box: " + text);
	}

	public void ClickOnSearchButton() {
		driver.findElement(search_button).click();
		log.info("Clicked on Search Button");
	}


	public void validateMyntraLogo() {
		boolean b = driver.findElement(nav_link_logo).isDisplayed();
		Assert.assertEquals("Navigation link logo",true, b);
	}
	
	public void validatePageTitleMatch(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		log.info("Page title matched: " + expectedTitle );
	}
	

	public void validateElementPresentInHeaderSection(String text) throws Exception {
		boolean b=false;

		switch(text.toLowerCase().trim()) {

		
		case "amazon prime logo":
			b = driver.findElement(nav_link_logo).isDisplayed();
			break;
		case "accounts and list link":
			b = driver.findElement(nav_link_profile).isDisplayed();
			break;
		case "return and orders":
			b = driver.findElement(nav_link_wishlist).isDisplayed();
			break;	
		case "cart link":
			b = driver.findElement(nav_link_cart).isDisplayed();
			break;
		case "search text box":
			b = driver.findElement(search_text_box).isDisplayed();
			break;
		default:
			log.fatal("Header Link Description is not present in the case. Please add link description first.");
			throw new Exception("Header Link Description is not present in the case. Please add link description first.");
		}

		if (b) {
			log.info("Header Link is displayed: " + text);
			Assert.assertEquals("Header Link displayed",true, b);
		}else {
			log.fatal("Header Link is not displayed: " + text);
			Assert.fail("Header Link is not displayed: " + text);
		}

	}

}
