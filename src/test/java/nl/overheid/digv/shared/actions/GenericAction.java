package nl.overheid.digv.shared.actions;

import org.openqa.selenium.WebDriver;
import org.slf4j.*;

import nl.overheid.digv.base.DIGVTestBase;
import nl.overheid.stf.cucumber.base.SeleniumDriverWrapper;

public class GenericAction {

  private static final Logger LOG = LoggerFactory.getLogger(GenericAction.class);
  private WebDriver driver;

  public GenericAction() {
    driver = SeleniumDriverWrapper.getInstance().getDriver();
  }

  //Run argument key name to set the url path, f.e.: -Dtest.app.path=https://www.rivm.nl
  //Using this will make sure no specific project urls are committed in your project repo.
  public static String TEST_APP_PATH = "test.app.path";

  /**
   * Navigate to the given URL, accept any alerts, set given cookies.
   * @param url to navigate to
   */
  public void browserReload(final String url) {
    LOG.debug("Initializing browser at url: {}", url);
    new DIGVTestBase().browserAlertAccept();
    SeleniumDriverWrapper.getInstance().setSessionCookies();
    driver.get(url);
    final String current = driver.getCurrentUrl();
    LOG.debug(current);
  }
}
