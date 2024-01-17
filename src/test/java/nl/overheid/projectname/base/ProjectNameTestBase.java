/*
 * Copyright the State of the Netherlands
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package nl.overheid.projectname.base;

import org.openqa.selenium.*;
import org.slf4j.*;

import nl.overheid.stf.cucumber.base.*;
import nl.overheid.stf.cucumber.shared.*;

/**
 * The TestBase class is used for generic Selenium functionality. 
 * This class extends the SeleniumTestFramework FunctionalTestBase.
 */
public class ProjectNameTestBase extends FunctionalTestBase{

  private static final Logger LOG = LoggerFactory.getLogger(ProjectNameTestBase.class);
  private WebDriver driver;
  private final LoremLibrary lorem;

  public ProjectNameTestBase() {
    super(SeleniumDriverWrapper.getInstance());
    driver = SeleniumDriverWrapper.getInstance().getDriver();
    lorem = LoremLibrary.getInstance();
  }

  /**
   * The LoremLibrary consists of standardized lorem ipsum text that can be used.
   */
  public void exampleLorem() {
    inputSetValue(By.id("test"), lorem.paragraph());
  }

  /**
   * Sets the value on the input-element with the given id-string.
   * @param id of element to set
   * @param value to set
   * @return WebElement
   */
  public WebElement inputSetValue(final String id, final String value) {
    return inputSetValue(By.id(id), value);
  }

  /**
   * Sets the value on the input-element with the given {@link By} locator.
   * The inputSetValue method of the STF FunctionalTestBase has two parameters that can be set. In this case the WebElement is given via the
   * waitForElementVisible method from the FunctionalTestBase.
   * @param locator {@link By} needed to locate a {@link WebElement}
   * @param value the value to set if element is found
   */
  public WebElement inputSetValue(final By locator, final String value) {
    return inputSetValue(waitForElementVisible(locator), value);
  }

  /**
   * You can override a method from the FunctionalTestBase to adjust it to your projects needs.
   * For example in this case the code always needs to print out the "Random debug message." before doing a buttonClick action.
   */
  @Override
  public WebElement buttonClick (final By locator) {
    //Some extra functionality.
    exampleLog();
    final WebElement el = driver.findElement(locator);
    return buttonClick(el);
  }

  public void exampleLog() {
    LOG.debug("Random debug message.");
  }

  /**
   * With the Randomizer helper class you can get random Integers or Strings of Integers.
   * @return a String of a random positive Integer
   */
  public String randomNumber() {
    return Randomizer.getInstance().getRandomIntAsString();
  }
}
