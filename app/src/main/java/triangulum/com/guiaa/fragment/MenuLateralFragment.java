package triangulum.com.guiaa.fragment;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.adapter.CidadesAdapter;
import triangulum.com.guiaa.model.Cidade;

public class MenuLateralFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private ImageView imageCheckAnterior;
    private RelativeLayout areaMinhaLocalizacao;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = (View) inflater.inflate(
                R.layout.fragment_menu_lateral, container, false);

        listView = (ListView)view.findViewById(R.id.lista_cidades);
        areaMinhaLocalizacao = (RelativeLayout)view.findViewById(R.id.area_minha_localizacao);
        areaMinhaLocalizacao.setOnClickListener(this);

        ArrayList<Cidade> cidades = new ArrayList<Cidade>();

        cidades.add(new Cidade(0, "Araguaina"));
        cidades.add(new Cidade(0, "Gurupi"));
        cidades.add(new Cidade(0, "Palmas"));
        cidades.add(new Cidade(0, "Paraíso"));

        CidadesAdapter cidadesAdapter = new CidadesAdapter(getActivity(),cidades);

        listView.setAdapter(cidadesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(imageCheckAnterior!=null){
                    imageCheckAnterior.setVisibility(View.GONE);
                }
                RelativeLayout row = (RelativeLayout) view;
                ImageView imagemCheck = (ImageView) row.getChildAt(1);
                imagemCheck.setVisibility(View.VISIBLE);
                imageCheckAnterior = imagemCheck;
            }
        });

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v==areaMinhaLocalizacao){
            if(imageCheckAnterior!=null){
                imageCheckAnterior.setVisibility(View.GONE);
            }
            ImageView imagemCheck = (ImageView)view.findViewById(R.id.imagem_check);
            imagemCheck.setVisibility(View.VISIBLE);
            imageCheckAnterior = imagemCheck;
        }
    }
}
