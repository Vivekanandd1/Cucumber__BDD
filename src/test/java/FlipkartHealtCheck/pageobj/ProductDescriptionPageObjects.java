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

import java.util.List;

public class ProductDescriptionPageObjects {

    private static final Logger log = LogManager.getLogger(ProductDescriptionPageObjects.class);

    private WebDriver driver;

    private By product_title = By.className("pdp-title");
    private By add_to_cart_button = By.xpath("//div[contains(text( ),'ADD TO BAG')]");

    //Section 3: Parameterize the constructor
    public ProductDescriptionPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateProductTileIsCorrectlyDisplayed(){
        if (driver.findElement(product_title).isDisplayed()){
            Assert.assertTrue(true);
            log.info("Product Title is displayed");
        }else{
            log.fatal("Product Title is not displayed");
            Assert.fail("Product Title is not displayed");
        }
    }

    public void ValidateAddToCartButtonIsCorrectlyDisplayed(){
        if (driver.findElement(add_to_cart_button).isDisplayed()){
            Assert.assertTrue(true);
            log.info("Add to Cart Button is displayed");
        }else{
            log.fatal("Add to Cart Button is not displayed");
            Assert.fail("Add to Cart Button is not displayed");
        }
    }


}
