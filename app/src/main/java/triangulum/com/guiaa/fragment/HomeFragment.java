package triangulum.com.guiaa.fragment;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpslocation.GooglePlayServiceLocation;
import com.gpslocation.GooglePlayServiceLocationUpdate;
import com.listeners.GooglePlayServiceLocationListener;
import com.listeners.GooglePlayServiceLocationUpdateListener;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.activities.HomeActivity;
import triangulum.com.guiaa.interfaces.AdvertiserListener;
import triangulum.com.guiaa.model.Category;
import triangulum.com.guiaa.model.City;
import triangulum.com.guiaa.services.ListAdvertiserService;
import triangulum.com.guiaa.utils.Utils;

public class HomeFragment extends Fragment implements View.OnClickListener,AdvertiserListener{

    private LinearLayout areaSelectionLocation;
    private TextView textViewCityChosen;
    private TextView textViewNameCity;
    private RelativeLayout areaWhatNear;
    private City cityChosen;
    private EditText editTextSeach;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        areaSelectionLocation = (LinearLayout)view.findViewById(R.id.area_selection_location);
        areaSelectionLocation.setOnClickListener(this);
        textViewCityChosen = (TextView) view.findViewById(R.id.item_location_current);
        textViewNameCity = (TextView) view.findViewById(R.id.name_city);
        areaWhatNear = (RelativeLayout)view.findViewById(R.id.area_what_near);
        editTextSeach = (EditText) view.findViewById(R.id.item_text_search);
        areaWhatNear.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==areaSelectionLocation) {
            HomeActivity activityHome = (HomeActivity) getActivity();
            activityHome.openDrawer();
        }
        if(v==areaWhatNear){

            if (Utils.verifyConection(getActivity())) {

                serviceListAdvertiser();
            }

        }
    }

    private void serviceListAdvertiser() {

            //String latitude = String.valueOf(location.getLatitude());
           // String longitude = String.valueOf(location.getLongitude());
            String latitude = "0";
            String longitude ="0";
            ListAdvertiserService serviceAdvertiser = new ListAdvertiserService(getActivity(),this,cityChosen.getId(),latitude,longitude,editTextSeach.getText().toString());
            serviceAdvertiser.execute();

    }

    public void setCityChosen(City city) {
        cityChosen = city;
        textViewCityChosen.setText(city.getNome());
        textViewNameCity.setText(city.getNome());
    }



    @Override
    public void onAdvertiserSuccess(ArrayList<Category> categories) {



    }

    @Override
    public void onAdvertiserError(String error) {

    }

    @Override
    public void onAdvertiserServerError() {

    }






}