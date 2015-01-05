package triangulum.com.guiaa.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.activities.HomeActivity;
import triangulum.com.guiaa.adapter.CitiesAdapter;
import triangulum.com.guiaa.model.City;

public class MenuLateralFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private ImageView imageCheckAnterior;
    private RelativeLayout areaMyLocation;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = (View) inflater.inflate(
                R.layout.fragment_menu_lateral, container, false);

        listView = (ListView)view.findViewById(R.id.lista_cidades);
        areaMyLocation = (RelativeLayout)view.findViewById(R.id.area_minha_localizacao);
        areaMyLocation.setOnClickListener(this);




        return view;
    }


    @Override
    public void onClick(View v) {
        if(v==areaMyLocation){
            if(imageCheckAnterior!=null){
                imageCheckAnterior.setVisibility(View.GONE);
            }
            ImageView imageCheck = (ImageView)view.findViewById(R.id.imagem_check);
            imageCheck.setVisibility(View.VISIBLE);
            imageCheckAnterior = imageCheck;
        }
    }

    public void setCities(ArrayList<City> cities) {
        Log.e("CITIES ","CITIES "+cities.size());
        final CitiesAdapter citiesAdapter = new CitiesAdapter(getActivity(),cities);

        listView.setAdapter(citiesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(imageCheckAnterior!=null){
                    imageCheckAnterior.setVisibility(View.GONE);
                }
                RelativeLayout row = (RelativeLayout) view;
                ImageView imageCheck = (ImageView) row.getChildAt(1);
                imageCheck.setVisibility(View.VISIBLE);
                imageCheckAnterior = imageCheck;

                City city = citiesAdapter.getItem(position);
                HomeActivity activityHome = (HomeActivity) getActivity();
                activityHome.setCityChosen(city);
            }
        });

    }
}
