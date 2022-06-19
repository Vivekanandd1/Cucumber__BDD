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

import java.util.List;

public class SearchPageObjects {

    private static final Logger log = LogManager.getLogger(SearchPageObjects.class);

    private WebDriver driver;

    private By search_refinement_categories_segment  = By.xpath("//div[@class='condition-render-verticalFilters condition-render-boundary-top']");
    private By product_link_list = By.xpath("//a[@target='_blank']");

    //Section 3: Parameterize the constructor
    public SearchPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateProductSearchIsSuccessfull(String Price){
        if (driver.findElement(search_refinement_categories_segment).isDisplayed()){
            Assert.assertTrue(true);
            log.info("Search Page is displayed because refinement category is displayed");
        }else{
            log.fatal("Search Page is not displayed because refinement category is not displayed");
            Assert.fail("Search Page is not displayed because refinement category is not displayed");
        }
    }

    public String ClickOnTheProductLink(int productIndex){
        //listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(product_link_list);
        log.info("Number of products searched: " + listOfProducts.size());

        //Link on the  link with argument productIndex
        listOfProducts.get(productIndex).click();
        log.info("Clicked on the Link in the List with index: " + productIndex +
                ". Link Text: " + listOfProducts.get(productIndex).getText());

        //return the text of the clicked link if further validation is required.
        return listOfProducts.get(productIndex).getText();

    }


}
