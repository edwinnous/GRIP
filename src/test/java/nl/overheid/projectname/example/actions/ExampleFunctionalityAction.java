package nl.overheid.projectname.example.actions;

import java.io.InputStream;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.slf4j.*;

import nl.overheid.projectname.base.ProjectNameTestBase;
import nl.overheid.stf.cucumber.base.SeleniumDriverWrapper;
import nl.overheid.stf.cucumber.shared.FileUtil;

public class ExampleFunctionalityAction {
  //Instantiate the Logger LOG.
  private static final Logger LOG = LoggerFactory.getLogger(ExampleFunctionalityAction.class);
  private final ProjectNameTestBase base;
  //Declare the WebDriver driver.
  private WebDriver driver;

  public ExampleFunctionalityAction() {
    base = new ProjectNameTestBase();
    //Instantiate and get the driver by calling the SeleniumDriverWrapper class and getDriver method.
    driver = SeleniumDriverWrapper.getInstance().getDriver();
  }

  /**
   * Action to check if the correct error message is shown.
   * @param statusMessage is the message you want to check
   */
  public void checkErrorMessage(final String statusMessage) {
    final String message = base.waitForElementVisible(By.xpath("//*[@role='alert']")).getText();
    Assert.assertTrue("The displayed error message {" + message + "}. Does not contain the expected error message {" + statusMessage + "}",
        message.contains(statusMessage));
  }

  /**
   * The standard logging is set to debug level.
   */
  public void loggerExample() {
    LOG.debug("Write your LOG.debug message here");
    LOG.error("Log message only displayed on error");
  }

  /**
   * Your usual Selenium driver functionality.
   */
  public void driverExample() {
    driver.findElement(By.id("test"));
  }

  /**
   * The base uploadFile method gets your current local path location and only needs the folder the file is placed in and the filename.
   * @param fileName
   */
  public void fileUploadExample() {
    base.uploadFile(By.id("test"), "files/example.txt");
  }

  /**
   * Example implementation of the {@link FileUtil} helper class. Check out the {@link FileUtil} class for all available methods.
   */
  public void fileLoading() {
    InputStream stream = FileUtil.getResourceAsStream("filepath");
    LOG.debug(stream.toString());
  }
}
