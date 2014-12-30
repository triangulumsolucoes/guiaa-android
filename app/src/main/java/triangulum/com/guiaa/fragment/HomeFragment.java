package triangulum.com.guiaa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.activities.HomeActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout areaSelecaoLocalizacao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        areaSelecaoLocalizacao = (LinearLayout)view.findViewById(R.id.area_selecao_localizacao);
        areaSelecaoLocalizacao.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==areaSelecaoLocalizacao){
            HomeActivity activityHome = (HomeActivity) getActivity();
            activityHome.openDrawer();
        }
    }
}