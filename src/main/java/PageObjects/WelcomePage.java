package PageObjects;

import ContextIs.Utils;
import org.openqa.selenium.By;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class WelcomePage extends Utils {

    private By _uploadLink = By.cssSelector("[href='/upload/']");
    private By _chooseFileButton = By.id("id_file");
    private By _uploadFileButton = By.cssSelector("[value='Upload file']");
    private By _welcomeUser = By.cssSelector("[class='container']>h1");

    public void userShouldBeAbleToUploadFile() {
        Utils.clickElementBy(_uploadLink);
        Utils.clickElementBy(_chooseFileButton);
    }

    public void userSelectsAfileToUpload(){
        // upload file from windows pop up
        StringSelection ss = new StringSelection("C:\\Users\\Admin\\Documents\\Screenshots\\featureFile.PNG");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        try
        {
            Robot robot = new Robot();
            Thread.sleep(3000);
            robot.mouseMove(50, 50);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Utils.waitForElementToBeClickable(_uploadFileButton, 5);
        Utils.clickElementBy(_uploadFileButton);
    }

    public void verifyFileIsSuccessfullyUploaded(){
        Utils.assertTextOfElement("Welcome user", _welcomeUser);
    }
}
