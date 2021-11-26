package stepDefinitions;

import POJO.addPlaceJson;
import POJO.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.resourceUrl;
import resources.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class stepDefinition extends utils {

    RequestSpecification res;
    ResponseSpecification r;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        r = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

    }


    @When("User calls the {string} with {string} http request")
    public void user_calls_the_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        resourceUrl url = resourceUrl.valueOf(resource);
        System.out.println(url.getResource());
        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(url.getResource()).then()
                    .spec(r).extract().response();
            System.out.println(response.asString());
        }else if(method.equalsIgnoreCase("GET")){
            response = res.when().get(url.getResource()).then().spec(r).extract().response();
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(),200);   //junit assertions
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        // Write code here that turns the phrase above into concrete actions


        assertEquals(getJsonPath(response,key).toString(),value);
    }
    @Then("verify that place_api created maps to {string} using {string}")
    public void verify_that_place_api_created_maps_to_using(String name, String resourceName) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_the_with_http_request(resourceName,"GET");    //to get the resource url
        String response_name = getJsonPath(response,"name");
        assertEquals(response_name, name);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
        r = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }


}
