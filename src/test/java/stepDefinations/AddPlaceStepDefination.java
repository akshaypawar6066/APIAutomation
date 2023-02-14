package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlaceStepDefination extends Utils {
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	 static String place_Id;
	TestDataBuild testDataBuild = new TestDataBuild();

	@Given("Add place API Payload with {string} {string} {string}")
	public void add_place_api_payload_with(String name, String language, String address) throws IOException {

		request = given().spec(requestSpecification()).body(testDataBuild.addPlacePayload(name, language, address));

	}

	@When("user calls {string}  with http {string} request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		// Constructor will be called with value of resource which you pass.
		APIResources resourceAPI = APIResources.valueOf(resource);
		 System.out.println(resourceAPI.getResource());
		if (httpMethod.equalsIgnoreCase("POST")) {
			response = request.relaxedHTTPSValidation().when().post(resourceAPI.getResource());

		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = request.relaxedHTTPSValidation().when().get(resourceAPI.getResource());
		}
	
	}

	@Then("the API call got succes with status code {int}")
	public void the_api_call_got_succes_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpValue) {

		assertEquals(getJsonPath(response, keyValue), ExpValue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
		// prepare req spec
		place_Id = getJsonPath(response, "place_id");
		System.out.println("Place_Id is:"+place_Id);
		request = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expName,actualName);
	}
	@Given("delete place API payload")
	public void delete_place_api_payload() throws IOException {
		
		request= given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_Id));
	}


}
