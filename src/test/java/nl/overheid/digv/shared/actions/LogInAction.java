package nl.overheid.digv.shared.actions;

import org.openqa.selenium.*;
import org.slf4j.*;

import nl.overheid.digv.base.DIGVTestBase;
import nl.overheid.digv.shared.stepdef.LogInStepDef.UserRoles;
import nl.overheid.stf.cucumber.base.SeleniumDriverWrapper;

/**
 * The user name and passwords should be defined in a properties file called test.users.properties.
 * Use following properties naming convention: 
 * <code>
 * admin.username = [name]
 * admin.password = [pass]
 * </code>
 * The runner argument for this file is test.users=[file path]
 */
public class LogInAction {

  private static final Logger LOG = LoggerFactory.getLogger(LogInAction.class);
  private final DIGVTestBase base;
  private WebDriver driver;

  public LogInAction() {
    base = new DIGVTestBase();
    driver = SeleniumDriverWrapper.getInstance().getDriver();
  }

  /**
   * Log user in with the specified role by using the UserRoles enumeration.
   * This is converted to lowercase because the properties file convention uses lowercase.
   * @param role to login as
   */
  public void loginUserAsRole(final UserRoles role) {
    final String user = role.name().toLowerCase();
    logIn(user);
  }

  /**
   * Log in with the specified credentials.
   * Go to the login page.
   * Get appropriate user credentials for provided role and attempt to login using the base functionality inputSetValue from the STF.
   * @param user user name of the role
   */
  private void logIn(final String user) {
    LOG.info("Logging in as user: '{}'", user);
    driver.get(base.getRunParameterAsString(GenericAction.TEST_APP_PATH) + "/user/login");
    base.inputSetValue("edit-name", base.getUserNameForRole(user));
    base.inputSetValue("edit-pass", base.getUserPassForRole(user)).sendKeys(Keys.RETURN);
  }

}
