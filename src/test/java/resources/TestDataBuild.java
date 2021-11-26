package resources;

import POJO.addPlaceJson;
import POJO.location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public addPlaceJson addPlacePayload(String name, String language, String address){
        addPlaceJson js = new addPlaceJson();
        js.setAccuracy(50);
        js.setAddress(address);
        js.setLanguage(language);
        js.setName(name);
        js.setPhone_number("(+91) 983 893 3937");
        js.setWebsite("https://rahulshettyacademy.com");

        List<String> list = new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        js.setTypes(list);

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        js.setLocation(l);
        return js;
    }

    public String deletePlacePayload(String placeID){
        return "{\n" +
                "    \"place_id\":\""+placeID+"\"\n" +
                "}\n";

    }

}
