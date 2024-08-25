package resourses;

import pojo.AddPlace;
import pojo.Locations;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name ,String language,String address){
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("59499302304");
        place.setWebsite("https://dsfsdfsdfsdf");
        place.setName(name);
        List<String> myLIst = new ArrayList<String>();
        myLIst.add("shoe park");
        myLIst.add("shop");
        place.setTypes(myLIst);
        Locations locations = new Locations();
        locations.setLat(-45.54554);
        locations.setLng(-12.3333);
        place.setLocation(locations);
        return place;
    }
}
