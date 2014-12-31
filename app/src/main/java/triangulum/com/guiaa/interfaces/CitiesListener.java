package triangulum.com.guiaa.interfaces;

import java.util.ArrayList;

import triangulum.com.guiaa.model.City;

public interface CitiesListener {

    public void onCitiesSuccess(ArrayList<City> cities);
    public void onCitiesServerError();
    public void onCitiesError(String error);
}