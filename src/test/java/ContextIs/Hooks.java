package ContextIs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Utils{

    BrowserSelector browserSelector = new BrowserSelector();
    LoadProperties loadProp = new LoadProperties();

    //A method under @Before will be invoked before any scenario
    @Before

    public void openBrowser() {

        browserSelector.setUpBrowser();
        driver.get(loadProp.getProperty("url"));

    }

    //A method under @After will be invoked after a scenario is completed
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed())
        {
            Utils.screenshotOfTheBrowserUsingCucumber(scenario);
        }
       driver.quit();
    }
}
