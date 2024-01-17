package nl.overheid.projectname;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

/**
 * This is the runner class for cucumber api tests.
 * There can be multiple runner classes.
 * A single runner class can handle multiple features.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/nightly/projectname/index_api.html",
        "json:target/nightly/projectname/nightly_api_report.json"
        },
    features = {"src/test/resources/example"},
    glue = {
        "nl.overheid.stf.shared.stepdef",
        "nl.overheid.projectname.shared",
        "nl.overheid.projectname.example.stepdef",
        },
    tags = "@api and not @ignore"
    )
public class ProjectNameRestSuiteTest {

}
