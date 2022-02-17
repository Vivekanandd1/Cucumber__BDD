package FlipkartHealtCheck.pageobj;

import java.awt.RenderingHints.Key;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/***
 * Author: Vivekanand Deshmukh
* Company: Myntra
* Date: 1-2-2022
* Description: Test Automation FW development
*/

public class BrandSelctionPage {
	private static final Logger log = LogManager.getLogger(BrandSelctionPage.class);
	WebDriver driver;
	
	private By  SearchBox = By.xpath("//input[@placeholder='Search for products, brands and more']");
	private By SearchBTn = By.xpath("//a[@class='desktop-submit']");
    private By filterBTn = By.xpath("//label/input[@value='men,men women']");
    private By CategoryBTn = By.xpath("//label[text()='Jeans']");

public BrandSelctionPage(WebDriver driver){
    this.driver = driver;
}
 public void BrandSelect() {
	 driver.findElement(SearchBox).sendKeys("jack&jones");
	 driver.findElement(SearchBTn).click();
	 log.info("user clicked for desired Brand");
 }
 public void GenFilter() {
	 driver.findElement(filterBTn);
	 log.info("User filtered the gender");
 }
 public void CategorySelection() {
	 driver.findElement(CategoryBTn).click();
	 log.info("Category selection done by user");
 }
}
