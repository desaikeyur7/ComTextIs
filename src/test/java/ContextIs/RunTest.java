package ContextIs;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = ".", tags = "@test", dryRun = false, monochrome = true, plugin = {"pretty, html:target/cukeReports"})

public class RunTest {
}
