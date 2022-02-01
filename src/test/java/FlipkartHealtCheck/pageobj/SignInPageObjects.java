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

public class SignInPageObjects {

    private static final Logger log = LogManager.getLogger(SignInPageObjects.class);

    private WebDriver driver;

    private By input_textbox_email  = By.className("desktop-linkButton");

    //Section 3: Parameterize the constructor
    public SignInPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void validateEmailInputTextBoxIsDisplayed(){
        if (driver.findElement(input_textbox_email).isDisplayed()){
            Assert.assertTrue(true);
            log.info("Email input box is displayed");
        }else{
            log.fatal("Email input box not displayed");
            Assert.fail("Email text box does not appear for login after clicking on Sign in button");
        }
    }

    public void enterTextInEmailTextBox(String text){
        log.info("Text entered in email id: " + text);
        driver.findElement(input_textbox_email).sendKeys(text);
    }

}
