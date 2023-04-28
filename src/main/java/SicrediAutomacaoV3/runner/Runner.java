package SicrediAutomacaoV3.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = {"SicrediAutomacaoV3.steps"},
        features = "classpath:features",
        tags = "@Teste",
        snippets = CAMELCASE

)


public class Runner {
}
