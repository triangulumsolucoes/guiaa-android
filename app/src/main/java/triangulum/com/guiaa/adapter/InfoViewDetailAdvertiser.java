package triangulum.com.guiaa.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.model.Advertiser;


public class InfoViewDetailAdvertiser implements InfoWindowAdapter {

    private final View myContentsView;

    private ArrayList<Advertiser> advertisers;

    private ImageView localImage;
    private TextView localName ;
    private TextView localAddressStreetNumber;
    private TextView localAddressDistrict;
    private TextView localAddressCityState;
    private TextView localPhoneNumber;

    public InfoViewDetailAdvertiser(Activity activity, ArrayList<Advertiser> advertisers) {
        this.advertisers = advertisers;
        myContentsView = activity.getLayoutInflater().inflate(R.layout.adapter_local, null);
    }

    public Advertiser getAdvertiser(String id) {
        for (Advertiser advertiser : advertisers) {
            if (id.equals(String.valueOf(advertiser.getId()))) {
                return advertiser;
            }
        }
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        mapView();
        Advertiser advertiser = getAdvertiser(marker.getTitle());
        loadData(advertiser);

        return myContentsView;
    }

    private void loadData(Advertiser advertiser) {
        if (advertiser != null) {
            localName.setText(advertiser.getName());

            if (advertiser.getAddress() != null) {
                String street = advertiser.getAddress().getStreet();
                String number = advertiser.getAddress().getNumber();
                if (street != null && number != null) {
                    localAddressStreetNumber.setText(street + ", " + number);
                } else if (street != null) {
                    localAddressStreetNumber.setText(street);
                }

                if (advertiser.getAddress().getDistrict() != null) {
                    localAddressDistrict.setText(advertiser.getAddress().getDistrict());
                }

                String city = advertiser.getAddress().getCity();
                String state = advertiser.getAddress().getState();

                if (city != null && state != null) {
                    localAddressCityState.setText(city + " - " + state);
                } else if (city != null) {
                    localAddressCityState.setText(city);
                } else if (state != null) {
                    localAddressCityState.setText(state);
                }
            }

            if(advertiser.getPhones() != null && advertiser.getPhones().size() > 0){
                localPhoneNumber.setText(advertiser.getPhones().get(0));
            }
        }
    }

    private void mapView() {
        localImage = ((ImageView) myContentsView.findViewById(R.id.item_imagem_local));
        localName = ((TextView) myContentsView.findViewById(R.id.item_nome_local));
        localAddressStreetNumber = ((TextView) myContentsView.findViewById(R.id.item_endereco_logradouro_numero));
        localAddressDistrict = ((TextView) myContentsView.findViewById(R.id.item_endereco_bairro));
        localAddressCityState = ((TextView) myContentsView.findViewById(R.id.item_endereco_cidade_estado));
        localPhoneNumber = ((TextView) myContentsView.findViewById(R.id.item_telefone_local));
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
}
