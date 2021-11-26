package resources;

public enum resourceUrl {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json")
    ;
    private String s;


    resourceUrl(String s) {
        this.s = s;
    }

    public String getResource(){
        return s;
    }
}
