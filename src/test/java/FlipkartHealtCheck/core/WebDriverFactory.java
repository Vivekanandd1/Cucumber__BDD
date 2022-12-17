package FlipkartHealtCheck.core;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	private static final Logger log = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver=null;
    public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
        switch(browser.toLowerCase()){
            case "chrome":
            	WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.info("Chrome Browser invoked");
                break;
            case "firefox":
            	WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                log.info("Firefox Browser invoked");
                break;
            case "headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
                log.info("Headless Chrome Browser invoked");
                break;
            default:
                log.fatal("No such browser is implemented.Browser name sent: " + browser);
                throw new Exception("No such browser is implemented.Browser name sent: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        log.info("Driver maximized and implicit time out set to 20 seconds");
        return driver;
    }

    public static void navigateToTheUrl(String url){
        driver.get(url);
        log.info("Browser navigated to the url: " + url);
    }

    public static void quitDriver(){
        driver.quit();
        log.info("Driver closed");
    }
    public static void switchBrowserToTab(){
        //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        log.info("List of windows found: "+handles.size());
        log.info("Windows handles: " + handles.toString());
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String orignial = it.next();//gives the parent window id
        String ProductDescription = it.next();//gives the child window id
        driver.switchTo().window(ProductDescription); // switch to product Descp
        log.info("Switched to the new window/tab");
    }

    public static String getBrowserName(){
        String browserDefault = "chrome"; //Set by default
        String browserSentFromCmd = System.getProperty("browser");

        if (browserSentFromCmd==null){
            return browserDefault;
        }else{
            return browserSentFromCmd;
        }
    }


}
