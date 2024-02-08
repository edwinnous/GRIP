package nl.overheid.projectname.shared.stepdef;

import io.cucumber.java.en.Given;
import nl.overheid.projectname.base.ProjectNameTestBase;
import nl.overheid.projectname.shared.actions.GenericAction;

public class GenericStepDef {

  private final ProjectNameTestBase base;
  private final GenericAction generic;

  public GenericStepDef() {
    base = new ProjectNameTestBase();
    generic = new GenericAction();
  }

  @Given("^RIVM is open$")
  public void rivm_application_is_open() {
    generic.browserReload(base.getRunParameterAsString(GenericAction.TEST_APP_PATH));
  }
}
