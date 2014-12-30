package triangulum.com.guiaa.services;

import android.app.Activity;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.interfaces.AdvertiserListener;
import triangulum.com.guiaa.model.*;
import triangulum.com.guiaa.model.Error;

/**
 * Created by francielly on 30/12/14.
 */
public class ListAdvertiserService extends ServiceBase {

    private AdvertiserListener advertiserListener;
    private Activity activity;
    private int city;
    private String latitude;
    private String longitude;
    private String query;

    private ArrayList<Category> categories;
    private Error error;
    private boolean isTimeout = false;

    public ListAdvertiserService(Activity activity, AdvertiserListener advertiserListener, int city,
                                 String latitude, String longitude, String query) {
        super(activity);
        this.activity = activity;
        this.advertiserListener = advertiserListener;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.query = query;
    }

    @Override
    protected void onPreExecute() {
        montaDialogFeedback(
                activity.getString(R.string.waiting), true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            JSONObject senderJson = getSenderJsonObject();

            String url = getContext().getResources()
                    .getString(R.string.url_list_advertisers);

            senderJson = doPost(url, senderJson.toString());

            if (getStatusCode() == HttpStatus.SC_OK) {
                Type listCategory = new TypeToken<List<Category>>() {
                }.getType();
                this.categories = (ArrayList<Category>) arrayListFromJsonArray(
                        listCategory, senderJson.getJSONArray("categories"));

                Type listAdvertiser = new TypeToken<List<Advertiser>>() {
                }.getType();
                ArrayList<Advertiser> advertisers = (ArrayList<Advertiser>) arrayListFromJsonArray
                        (listAdvertiser, senderJson.getJSONArray("results"));

               attributionAdvertiserCategory(advertisers);

            } else if (getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                error = (triangulum.com.guiaa.model.Error) objectFromJson(Error.class,
                        senderJson.getJSONObject("error"));
            }
        } catch (SocketTimeoutException e) {
            isTimeout = true;
        } catch (IOException e) {
        } catch (JSONException e) {
        }

        return null;
    }

    private JSONObject getSenderJsonObject() throws JSONException {
        JSONObject senderJson = new JSONObject();
        JSONObject coordinatesJson = new JSONObject();
        JSONObject locationJson = new JSONObject();

        coordinatesJson.put("latitude", latitude);
        coordinatesJson.put("longitude", longitude);

        locationJson.put("coordinates", coordinatesJson);
        locationJson.put("city", city);

        senderJson.put("location", locationJson);
        senderJson.put("query", query);

        return senderJson;
    }

    private void attributionAdvertiserCategory(ArrayList<Advertiser> advertisers){
        for(Category category : categories){
            ArrayList<Advertiser> advertisersToCategory = new ArrayList<Advertiser>();
            for(Advertiser advertiser : advertisers){
                if(category.getId() == advertiser.getCategoryid()){
                    advertisersToCategory.add(advertiser);
                }
            }
            category.setAdvertisers(advertisersToCategory);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dismissDialog();

        if(getStatusCode() == HttpStatus.SC_OK){
            advertiserListener.onAdvertiserSuccess(categories);
        }else if(getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR){
            advertiserListener.onAdvertiserServerError();
        }else if(getStatusCode() == 0){
            String msg;
            if (isTimeout){
                msg = activity.getString(R.string.connection_timeout);
            }else{
                msg = activity.getString(R.string.lost_connection);
            }
            advertiserListener.onAdvertiserError(msg);
        }else if(error == null){
            advertiserListener.onAdvertiserServerError();
        }else {
            advertiserListener.onAdvertiserError(error.getError());
        }

    }
}