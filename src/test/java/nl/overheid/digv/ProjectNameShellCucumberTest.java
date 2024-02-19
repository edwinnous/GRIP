package nl.overheid.digv;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

/**
 * This is the runner class for cucumber tests executing shell commands
 * Use the tags @linux or @windows to run the correct scenarios on your local platform.
 * 
 * Test results may vary depending on your local machine and will likely have some failing tests in this example if you are running this on a Windows environment.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/cucumber/nightly/example_shell/index.html",
        "json:target/cucumber/nightly/example_shell/nightly_report.json"
        },
    features = {"src/test/resources/Example"},
    glue = {"nl.overheid.stf.shared.stepdef"},
    tags = "@shell and not @windows or @ignore"
    )
public class ProjectNameShellCucumberTest {

}
