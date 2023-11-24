package com.trelloBoard.Runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports"},
        features = "src/test/resources/Features/",
        glue = "com/trelloBoard/Step_Def",
        dryRun = false,
        tags = "@apıTest"
)

public class CukesRunner {
}
