package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Locations;
import resourses.TestDataBuild;
import resourses.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;

public class StepDefitation extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild(); // data befindet sich in TestDataBuild class

    @Given("Add place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(dataBuild.addPlacePayload(name,language,address));
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = res.when().post("maps/api/place/add/json")
                .then().spec(resspec).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String Expectedvalue) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        Assert.assertEquals(jsonPath.get(key).toString(), Expectedvalue);
    }
}
