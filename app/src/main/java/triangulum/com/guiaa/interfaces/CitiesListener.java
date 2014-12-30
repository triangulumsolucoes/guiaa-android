package triangulum.com.guiaa.interfaces;

import java.util.ArrayList;

import triangulum.com.guiaa.model.Cidade;

public interface CitiesListener {

    public void onCitiesSuccess(ArrayList<Cidade> cities);
    public void onCitiesServerError();
    public void onCitiesError(String error);
}