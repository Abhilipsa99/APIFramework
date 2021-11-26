package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {          //To set preconditions before running any scenario
        stepDefinition s = new stepDefinition();      //This method is called only when delete place scenario is run individually without
        //running addPlace scenario to get the place id.
        if (stepDefinition.place_id == null) {
            s.add_place_payload_with("Informatica Netherland", "Russian", "Van Koetsveldstraat 25\n" +
                    "3532 ES Utrecht (NL)");         //Create Request Specification
            s.user_calls_the_with_http_request("AddPlaceAPI", "POST");
            s.verify_that_place_api_created_maps_to_using("Informatica Netherland", "GetPlaceAPI");

        }
    }
}
