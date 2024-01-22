package nl.overheid.projectname.shared.stepdef;

import java.io.IOException;
import java.util.List;

import io.cucumber.java.en.*;
import nl.overheid.projectname.shared.EndPoint;
import nl.overheid.stf.cucumber.base.RestAssuredWrapper;
import nl.overheid.stf.cucumber.shared.FileUtil;

public class RestAssuredStepDef {

  private RestAssuredWrapper rest;

  public RestAssuredStepDef() throws IOException {
    rest = RestAssuredWrapper.getInstance();
    rest.setQueryParam("_format", "json");
  }
//
//  @Given("the user requests a session token")
//  public void the_user_requests_session_token() {
//    rest.sendGetRequestNoAuth(EndPoint.TOKEN.getPath());
//  }
//
//  @And("the session token is set as header")
//  public void the_session_token_set_as_header() {
//    rest.setCsrfToken(rest.getResponse().asString());
//  }
//
//  @When("the user sends an unauthorized GET request at endpoint '{}' querying value {string}")
//  public void the_user_sends_an_unauthorized_request_at_endpoint(final EndPoint ep, final String authorId) {
//    rest.sendGetRequestNoAuth(ep.getPath(authorId));
//  }
//
//  @When("the user sends an unauthorized POST request to endpoint '{}':")
//  public void the_user_sends_an_unauthorized_request_to_create_author(final EndPoint ep, final List<String> payload) {
//    rest.sendPostRequestNoAuth(ep.getPath(), payload.get(0));
//  }

//  @When("the user sends an unauthorized POST request to endpoint '{}' with payload from file: {string}")
//  public void the_user_sends_an_unauthorized_request_to_create_author_from_file(final EndPoint ep, final String fileName) {
//    final String payload = FileUtil.getResourceAsString("example.restassured/json/" + fileName, "\n");
//    rest.sendPostRequestNoAuth(ep.getPath(), payload);
//  }

}
