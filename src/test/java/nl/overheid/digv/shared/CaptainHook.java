 package nl.overheid.digv.shared;

import org.slf4j.*;

import io.cucumber.java.After;
import nl.overheid.digv.base.DIGVTestBase;

/**
 * If your projects needs @Before or @After hooks it can be defined in a Hooks class like this.
 */

public class CaptainHook {

  private static final Logger LOG = LoggerFactory.getLogger(DIGVTestBase.class);
  /**
   * Log this info message after each scenario.
   */
  @After
  public void someAfterHook() {
    LOG.info("After hook message");
  }
}
