package SwagLabs.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/SwagLabs/cucumber/resources/features",
        glue = "SwagLabs.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"},
        tags = "@Login or @ProductPage or @Checkout or @Filter"
)
public class RunAutoTest {
}