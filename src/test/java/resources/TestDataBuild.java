package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceSerialization;
import pojo.Location;

public class TestDataBuild {
	public AddPlaceSerialization addPlacePayload(String name, String language, String address) {
		AddPlaceSerialization p = new AddPlaceSerialization();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("6564646465");
		p.setName(name);
		p.setWebsite("abcbooks.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Book Shop");
		myList.add("Market");
		p.setTypes(myList);
		Location location = new Location();
		location.setLat(-30.40520);
		location.setLng(12.35);
		p.setLocation(location);
		return p;
	}
	
	public String deletePlacePayload(String place_Id)
	{
		 return  "{\r\n"
		   + "    \"place_id\":\""+place_Id+"\"\r\n"
		   + "}";
	}

}
