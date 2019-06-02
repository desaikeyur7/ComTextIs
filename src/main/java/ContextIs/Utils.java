package ContextIs;

import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sun.deploy.cache.Cache.copyFile;

public class Utils extends BasePage {

    // Resuable method for click
    public static void clickElementBy(By by) {
        driver.findElement(by).click();

    }

    public static void enterText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    // Reusable method to check if emelent is displayed
    public static void webElementDisplayed(By by) {
        try {
            driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element is not displayed: " + by);
            throw (e);
        }
    }

    public static void elementIsDisplayed(By by) {
        driver.findElement(by).isDisplayed();
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    // Reusable method to assert text of element
    public static void assertTextOfElement(String expected, By by) {
        try {
            String actual = driver.findElement(by).getText();
            Assert.assertEquals(expected, actual);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Locator OR Element or Text in element is not present: " + by);
            throw (e);
        }
    }

    // Reusable method for Explicit wait for element to be displayed/visible
    public static void waitForElementDisplay(By by, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Locator OR Element is not present within the given wait time: " + by);
            throw (e);
        }
    }

    // Reusable method to check element is clickable
    public static void waitForElementToBeClickable(By by, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(by));

        } catch (NoSuchElementException e) {
            System.out.println("Invalid Locator OR Element is not present or clickable within the given wait time: " + by);
            throw (e);
        }
    }

    // Reusable method to scroll up or down
    public static void scrollUpOrDown(int scroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + scroll + ")");
    }

    // Reusable method to scroll and click on an element
    public static void scrollToElement(By by) {
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            je.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (ElementNotVisibleException e) {
            System.out.println("Element is not visible: " + by);
            throw (e);
        }
    }

    // Reusable method to scroll and click on an element
    public static void scrollAndClickOnElement(By by, int time) {
        try {
            // scroll to element
            JavascriptExecutor je = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            je.executeScript("arguments[0].scrollIntoView(true);", element);

            // wait for element to be visible
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(by));

            // click on element
            element.click();
            System.out.println(element.getText());
        } catch (ElementNotVisibleException e) {
            System.out.println("Element is not visible: " + by);
            throw (e);
        }
    }

    // Method to verify element contains text or part of text
    public static void verifyElementContainsText(By by, String text) {
        try {
            driver.findElement(by).getText().contains(text);

        }
        catch (NoSuchElementException e) {
            System.out.println("Element not found using locator:" + by);
            throw (e);
        }
    }

    // Method to move to an element in a page and click the element
    public static void moveToElementAndClick(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    // Method to verify title of the page
    public static void verifyPageTitle(String expectedTitle) {

        String actualTitle = driver.getTitle();
        System.out.println("The actual title of page is " + actualTitle);
        actualTitle.contains(expectedTitle);

        if (actualTitle.equalsIgnoreCase(expectedTitle))
            System.out.println("Title Matched");
        else
            System.out.println("Title didn't match");
    }

    //Cucumber screenshot method
    public static void screenshotOfTheBrowserUsingCucumber(Scenario scenario)
    {
        String timeStamp = new SimpleDateFormat("dd.MM.yy.HH.mm.ss").format(new Date());
        String screenshotName = scenario.getName().replaceAll("[.,;:?!]", "")+timeStamp+".png";
        try
        {
            //This takes a screenshot from the driver at save it to the specified location
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //Building up the destination path for the screenshot to save
            //Also make sure to create a folder 'screenshots' within the cucumber-reports folder
            File destinationPath = new File(System.getProperty("user.dir")+
                    "/target/cucumber-reports/extent-reports/screenshots/"+screenshotName+".png");
            //Copy taken screenshot from source location to the destination location
            copyFile(sourcePath,destinationPath );
            scenario.write("!!-----------.......Scenario Failed......----------!! Please see attached screenshot for the error/issue");
            //attach the screenshot to our report
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"image/png");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
