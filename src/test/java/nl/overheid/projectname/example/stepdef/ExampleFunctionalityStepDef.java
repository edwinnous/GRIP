package nl.overheid.projectname.example.stepdef;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import nl.overheid.projectname.base.ProjectNameTestBase;
import nl.overheid.projectname.example.actions.ExampleFunctionalityAction;

public class ExampleFunctionalityStepDef {

  private final ProjectNameTestBase base;
  private final ExampleFunctionalityAction exampleAction;

  public ExampleFunctionalityStepDef() {
    base = new ProjectNameTestBase();
    exampleAction = new ExampleFunctionalityAction();
  }

//  @Then("^the user navigates to the search page$")
//  public void go_to_search_page() {
//    base.buttonClick(By.id("edit-submit-zoekresultaten"));
//  }

  //If a StepDef does more than 1 thing it should be moved to a corresponding Action class.
//  @Then("^the status message contains '(.+)'$")
//  public void check_status_message(final String statusMessage) {
//    exampleAction.checkErrorMessage(statusMessage);
//  }
//
//  @Then("^the page title is '(.+)'$")
//  public void check_page_title(final String pageTitle) {
//    Assert.assertEquals(pageTitle, base.waitForElementVisible(By.xpath("//h1")).getText());
//  }
}
