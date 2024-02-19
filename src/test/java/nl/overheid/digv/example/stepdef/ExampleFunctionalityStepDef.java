package nl.overheid.digv.example.stepdef;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.overheid.digv.base.DIGVTestBase;
import nl.overheid.digv.example.actions.ExampleFunctionalityAction;

public class ExampleFunctionalityStepDef {

  private final DIGVTestBase base;
  private final ExampleFunctionalityAction exampleAction;

  public ExampleFunctionalityStepDef() {
    base = new DIGVTestBase();
    exampleAction = new ExampleFunctionalityAction();
  }
 
  @Then("^the user navigates to the search page$")
  public void go_to_search_page() {
    base.buttonClick(By.id("edit-submit-zoekresultaten"));
  }

  @When("^the user enters the username '(.+)'$")
  public void enter_username(final String userName) {
    base.inputSetValue(By.id("username"), userName);
  }
  

  @When("^the user enters the password '(.+)'$")
  public void enter_password(final String password) {
    base.inputSetValue(By.id("password"), password);
  }

  @When("^the user clicks sign in$")
  public void click_sign_in() {
    base.buttonClick(By.name("login"));
  }

  @When("^the user clicks Zaken$")
  public void click_zaken() {
	base.browserWait(Duration.ofSeconds(4));
    base.buttonClick(base.waitForElementVisible(By.xpath("//a[@href=\"/cases\"]")));
  }
  
//  If a StepDef does more than 1 thing it should be moved to a corresponding Action class.
  @Then("^the status message contains '(.+)'$")
  public void check_status_message(final String statusMessage) {
    exampleAction.checkErrorMessage(statusMessage);
  }

  @Then("^the page title is '(.+)'$")
  public void check_page_title(final String pageTitle) {
	base.browserWait(Duration.ofSeconds(4));
    Assert.assertEquals(pageTitle, base.waitForElementPresent(By.xpath("//title")).getText());
  }
}
