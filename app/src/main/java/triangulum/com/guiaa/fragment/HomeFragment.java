package triangulum.com.guiaa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.activities.HomeActivity;
import triangulum.com.guiaa.model.City;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout areaSelectionLocation;
    private TextView textViewCityChosen;
    private TextView textViewNameCity;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        areaSelectionLocation = (LinearLayout)view.findViewById(R.id.area_selection_location);
        areaSelectionLocation.setOnClickListener(this);
        textViewCityChosen = (TextView) view.findViewById(R.id.item_location_current);
        textViewNameCity = (TextView) view.findViewById(R.id.name_city);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==areaSelectionLocation){
            HomeActivity activityHome = (HomeActivity) getActivity();
            activityHome.openDrawer();
        }
    }

    public void setCityChosen(City city) {
        textViewCityChosen.setText(city.getNome());
        textViewNameCity.setText(city.getNome());
    }
}