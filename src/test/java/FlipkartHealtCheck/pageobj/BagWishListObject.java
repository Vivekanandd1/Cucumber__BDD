package FlipkartHealtCheck.pageobj;

/***
 * Author: Vivekanand Deshmukh
* Company: Myntra
* Date: 1-2-2022
* Description: Test Automation FW development
*/



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;



public class BagWishListObject {
	private static final Logger log = LogManager.getLogger(BagWishListObject.class);
	
     WebDriver driver;
    
	
	private By BagButton = By.xpath("//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']");
    private  By AddFromWishlist = By.xpath("//div[@class='button-base-button emptyCart-base-wishlistButton ']");
	
	 public BagWishListObject(WebDriver driver){
	        this.driver = driver;
	    }
	
	 
	 public void ClickOnBag(){
	        driver.findElement(BagButton).click();
	        log.info("user clicked on bag");
	 }
	 
	 public void ClickOnWishlist(){
	       driver.findElement(AddFromWishlist).click();
	        log.info("user clicked on wishlist");
	 }
}
