package nl.overheid.digv.shared.stepdef;

import io.cucumber.java.en.Given;
import nl.overheid.digv.base.DIGVTestBase;
import nl.overheid.digv.shared.actions.GenericAction;

public class GenericStepDef {

  private final DIGVTestBase base;
  private final GenericAction generic;

  public GenericStepDef() {
    base = new DIGVTestBase();
    generic = new GenericAction();
  }

  @Given("^RIVM is open$")
  public void rivm_application_is_open() {
    generic.browserReload(base.getRunParameterAsString(GenericAction.TEST_APP_PATH));
  }
  
  @Given("^Atabase is open$")
  public void atabase_is_open() {
    generic.browserReload(base.getRunParameterAsString(GenericAction.TEST_APP_PATH));
  }
}
