package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// Write a code that will give you place_Id
		// Execute this code when place_Id is null.

		AddPlaceStepDefination addPlace = new AddPlaceStepDefination();
		if (AddPlaceStepDefination.place_Id == null) {
			addPlace.add_place_api_payload_with("AKSHAY", "Frensh", "On Mars");
			addPlace.user_calls_with_http_request("addPlaceAPI", "POST");
			addPlace.verify_place_id_created_maps_to_using("AKSHAY", "getPlaceAPI");
		}
	}
}

