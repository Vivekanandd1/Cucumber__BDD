package AjioWebAppCheck;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features1",
		glue = "AjioWebAppCheck.stepdefs1",
		tags = " ",
		plugin = {
				"pretty",
				"html:target/html/htmlreport2.html",
				"json:target/json/file.json",
		},
		dryRun = false
		)

public class WebTest {
	

}
