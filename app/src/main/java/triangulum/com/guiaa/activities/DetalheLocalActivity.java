package triangulum.com.guiaa.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.FrameLayout;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.fragment.MapaDetalheLocalFragment;
import triangulum.com.guiaa.model.Advertiser;
import triangulum.com.guiaa.model.Category;


public class DetalheLocalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_local);

        createActionBar();
        showMap();
    }

    private void createActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.actionbar_detail));
        actionBar.setHomeButtonEnabled(true);
    }

    private void showMap(){
        MapaDetalheLocalFragment fragment = new MapaDetalheLocalFragment();

//        FragmentTransaction transaction = getSupportFragmentManager()
//                .beginTransaction();
//        transaction.add(R.id.area_view_mapa, fragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.area_view_mapa, fragment).commit();

//        Bundle args = new Bundle();
//
//        Category category = new Category(1, "Categoria tete", null);
//        Advertiser advertiser = new Advertiser(1, 1, "Teste", null, "-10.185897","-48.326807", "email@email.com",
//                null, true, true, null);
//
//        args.putSerializable("advertiserMapDetail", advertiser);
//
//        args.putSerializable("categoryMapDetail", category);
//        fragment.setArguments(args);
//        //fragment.setArguments(getIntent().getExtras());
//
//        transaction.commit();
    }
}
