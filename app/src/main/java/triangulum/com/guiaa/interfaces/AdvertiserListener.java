package triangulum.com.guiaa.interfaces;

import java.util.ArrayList;

import triangulum.com.guiaa.model.Category;

/**
 * Created by francielly on 30/12/14.
 */
public interface AdvertiserListener {

    public void onAdvertiserSuccess(ArrayList<Category> categories);
    public void onAdvertiserError(String error);
    public void onAdvertiserServerError();
}
