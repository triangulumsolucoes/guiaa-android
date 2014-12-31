package triangulum.com.guiaa.services;

import android.app.Activity;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;


import triangulum.com.guiaa.R;
import triangulum.com.guiaa.interfaces.CitiesListener;
import triangulum.com.guiaa.model.*;
import triangulum.com.guiaa.model.Error;

public class ListCitiesService extends ServiceBase {

    private Activity activity;
    private CitiesListener citiesListener;

    private ArrayList<City> cities;

    private Error error;
    private boolean isTimeout = false;

    public ListCitiesService(Activity context, CitiesListener citiesListener) {
        super(context);
        this.citiesListener = citiesListener;
        this.activity = context;
    }

    @Override
    protected void onPreExecute() {
        montaDialogFeedback(
                activity.getString(R.string.waiting), true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        String json = null;

        Log.e("BAIXANDO CIDADES ", "BAIXANDO CIDADES");

        try {

            String url = getContext().getResources()
                    .getString(R.string.url_list_cities);
            json = doPost(url);

            if (getStatusCode() == HttpStatus.SC_OK) {
                Log.e("JSON ","JSON "+json);
                JSONArray arrayCities = new JSONArray(json);
                Type listType = new TypeToken<List<City>>() {
                }.getType();
                this.cities = (ArrayList<City>) arrayListFromJsonArray(
                        listType, arrayCities);
            } else if (getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                JSONObject objectJson = new JSONObject(json);
                error = (Error) objectFromJson(Error.class,
                        objectJson.getJSONObject("error"));
            }
        } catch (SocketTimeoutException e) {
            isTimeout = true;
        } catch (IOException e) {
        } catch (JSONException e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dismissDialog();

        if(getStatusCode() == HttpStatus.SC_OK){
            citiesListener.onCitiesSuccess(cities);
        }else if(getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR){
            citiesListener.onCitiesServerError();
        }else if(getStatusCode() == 0){
            String msg;
            if (isTimeout){
                msg = activity.getString(R.string.connection_timeout);
            }else{
                msg = activity.getString(R.string.lost_connection);
            }
            citiesListener.onCitiesError(msg);
        }else if(error == null){
            citiesListener.onCitiesServerError();
        }else {
            citiesListener.onCitiesError(error.getError());
        }
    }
}
