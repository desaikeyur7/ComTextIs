package PageObjects;

import ContextIs.Utils;
import org.openqa.selenium.By;

public class HomePage extends Utils {

    private By _login = By.cssSelector("[href='/accounts/login/']");

    public void userShouldBeAbleToNavigateToLoginPage(){
        Utils.clickElementBy(_login);
    }
}
