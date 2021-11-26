package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", plugin = { "pretty", "html:target/cucumber-reports.html" },/*plugin="json:target/jsonReports/cucumber_report.json",*/glue={"stepDefinitions"}  /*, tags= "@DeletePlace"*/)
public class TestRunner {
}
