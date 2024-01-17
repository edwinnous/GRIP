package nl.overheid.projectname.shared.stepdef;

import io.cucumber.java.en.Given;
import nl.overheid.projectname.shared.actions.LogInAction;

public class LogInStepDef {

  public enum UserRoles {
    ADMIN, FUNCTIONEELBEHEERDER, GEBRUIKER;
  }

  @Given("^the user is logged in as (.+)$")
  public void logged_in_as(final UserRoles role) {
    new LogInAction().loginUserAsRole(role);
  }

}
