package ContextIs;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

public class BrowserSelector extends Utils {

    LoadProperties loadprop = new LoadProperties();

    public void setUpBrowser(){
        String browser = loadprop.getProperty("browser");
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", "src\\test\\Resources\\BrowserDrivers\\geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
            driver = new FirefoxDriver();
            driver.manage().window().fullscreen();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver","src/test/Resources/BrowserDrivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("_disable-extensions");
            options.addArguments("_disable-setUpBrowser-side-navigation");
            options.addArguments("_incognito");
            options.addArguments("_disable-blink-features=BlockCredentialedSubresources");
            driver=new ChromeDriver(options);
            driver.manage().window().fullscreen();
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("ie")){
            //set path to Edge.exe
            System.setProperty("webdriver.ie.driver","src/test/Resources/BrowserDrivers/IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");
            options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

            //create Edge instance
            driver = new InternetExplorerDriver();
            driver.manage().window().fullscreen();
        }
        else{
            //If no browser passed throw exception
            System.out.println("Browser name typed wrong: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
