package PageObjects;

import ContextIs.LoadProperties;
import ContextIs.Utils;
import org.openqa.selenium.By;

public class LoginPage extends Utils {

    private By _username = By.id("id_username");
    private By _password = By.id("id_password");
    private By _loginButton = By.cssSelector("[value='Login']");
    private By _welcomeUser = By.cssSelector("[class='container']>h1");

    public void userShouldBeAbleToLoginAndNavigateToWelcomePage(String username, String password){
        Utils.enterText(_username, (username));
        Utils.enterText(_password, (password));
        Utils.clickElementBy(_loginButton);
    }

    public void verifyUserIsOnWelcomePage(){
        Utils.assertTextOfElement("Welcome user", _welcomeUser);
    }
}
