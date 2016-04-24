package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rrt on 4/23/2016.
 */
public class CommonAPI {

    public WebDriver driver = null;
    @Parameters({"usecloud","userName","accessKey","os","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean usecloud,@Optional("rahmanww") String userName,@Optional("")
    String accessKey, @Optional("Windows 8") String os,@Optional("firefox") String browserName,@Optional("34")
    String browserVersion,@Optional("http://www.cnn.com") String url)throws IOException {
        if(usecloud==true){
            //run in cloud
            getCloudDriver(userName,accessKey,os,browserName,browserVersion);
        }else{
            //run in local
            getLocalDriver(browserName);
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        }

    public WebDriver getLocalDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","Generic/browser-driver/chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "Generic/browser-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;

    }

    public WebDriver getCloudDriver(String userName,String accessKey,String os, String browserName,
                                    String browserVersion)throws IOException {{

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platform", os);
        cap.setBrowserName(browserName);
        cap.setCapability("version",browserVersion);
        driver = new RemoteWebDriver(new URL("http://"+userName+":"+accessKey+
        "@ondemand.saucelabs.com:80/wd/hub"), cap);

            return driver;
        }
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void takeEnterKeys(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public List<WebElement> getListOfWebElements(String locator) {

        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));

        return list;
    }

    public List<String> getListOfString(List<WebElement> list) {

        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }

        return items;
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
    Select select = new Select(element);
    select.selectByVisibleText(value);
    }

}
