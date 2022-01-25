package FlipkartHealtCheck.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		glue = "FlipkartHealtCheck.stepdefs",
		tags = " ",
		plugin = {
				"pretty",
				"html:target/html/htmlreport1.html",
				"json:target/json/file.json",
		},
		dryRun = false
		)


public class HealthTest {

}
