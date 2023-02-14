Feature: validating plcae API's
@AddPlace @Regression
Scenario Outline: Verify place is getting added successful using add place API
Given Add place API Payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI"  with http "post" request
Then the API call got succes with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
 |name  |language|address             |           
 |AHouse|English |World cross centre  |
# |Bhouse|Spanish   |World trade centre|

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working 
Given delete place API payload
When user calls "deletePlaceAPI"  with http "post" request
Then the API call got succes with status code 200
And "status" in response body is "OK"