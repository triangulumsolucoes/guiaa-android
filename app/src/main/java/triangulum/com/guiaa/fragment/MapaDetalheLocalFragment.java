package triangulum.com.guiaa.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.adapter.InfoViewDetailAdvertiser;
import triangulum.com.guiaa.model.Advertiser;
import triangulum.com.guiaa.model.Category;

public class MapaDetalheLocalFragment extends Fragment implements OnMarkerClickListener, OnMyLocationChangeListener {

    private Advertiser advertiser;
    private Category category;
    private InfoViewDetailAdvertiser adapter;
    private View view;
    private GoogleMap map;
    private Marker marker;
    private LatLng locLatLng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.view_map, container, false);

//        advertiser = (Advertiser) getArguments().getSerializable("advertiserMapDetail");
//        category = (Category) getArguments().getSerializable("categoryMapDetail");

        createMap();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (map != null) {
            map.setOnMyLocationChangeListener(null);
            map.setOnCameraChangeListener(null);
        }
    }

    private void createMap() {
            map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            map.setOnMarkerClickListener(this);
//            map.setMyLocationEnabled(true);
//
//            if (advertiser.getLatitude() != null && advertiser.getLongitude() != null) {
//                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(advertiser.getLatitudeFloat(),
//                        advertiser.getLongitudeFloat()), 10.0f));
//
//                setSelectionInMap(advertiser.getLatitudeFloat(),
//                        advertiser.getLongitudeFloat());
//            } else {
//                map.setOnMyLocationChangeListener(this);
//            }
    }

    private void setSelectionInMap(float latitude, float longitude) {
        ArrayList<Advertiser> advertisers = new ArrayList<Advertiser>();
        advertisers.add(advertiser);

        adapter = new InfoViewDetailAdvertiser(getActivity(), advertisers);
        map.setInfoWindowAdapter(adapter);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(latitude, longitude));
        markerOptions.title(String.valueOf(advertiser.getId()));
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(category.getColor()));

        Marker marker = map.addMarker(markerOptions);

        if (isMarkerAtivo(markerOptions.getTitle())) {
            marker.showInfoWindow();
        }
    }

    private void closeInfoWindow() {
        if ((this.marker != null) && (this.marker.isInfoWindowShown())) {
            this.marker.hideInfoWindow();
        }
    }

    private boolean isMarkerAtivo(String title) {
        if (marker != null) {
            if (marker.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    @Override
    public void onMyLocationChange(Location location) {
        if (locLatLng == null) {
            locLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(locLatLng,
                    10.0f));
        }
    }
}
