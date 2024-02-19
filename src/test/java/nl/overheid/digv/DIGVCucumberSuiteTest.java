package nl.overheid.digv;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;
import nl.overheid.stf.cucumber.base.CucumberBase;

/**
 * This is the runner class for cucumber tests.
 * There can be multiple runner classes.
 * A single runner class can handle multiple features.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/nightly/projectname/index_e2e.html",
        "json:target/nightly/projectname/nightly_e2e_report.json"
        },
    // Location of the feature files that will be run.
    features = {"src/test/resources/example"},
    // Step definitions, hooks and other glue code locations need to be added here
    // by specifying the package location.
    glue = {
        "nl.overheid.stf.cucumber.shared.action",
        "nl.overheid.stf.cucumber.shared",
        "nl.overheid.stf.shared.stepdef",
        "nl.overheid.digv.shared",
        "nl.overheid.digv.example.stepdef",
        },
    //Only features that have the specified set of tags will be executed.
    tags = "@nightly and not @ignore"
    )
public class DIGVCucumberSuiteTest extends CucumberBase {

}
