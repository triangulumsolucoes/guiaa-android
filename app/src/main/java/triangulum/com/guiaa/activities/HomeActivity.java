package triangulum.com.guiaa.activities;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.fragment.HomeFragment;
import triangulum.com.guiaa.fragment.MenuLateralFragment;
import triangulum.com.guiaa.interfaces.CitiesListener;
import triangulum.com.guiaa.model.City;
import triangulum.com.guiaa.services.ListCitiesService;
import triangulum.com.guiaa.utils.Utils;

public class HomeActivity extends ActionBarActivity implements CitiesListener {


    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuLateralFragment fragmentMenuLateral;
    private HomeFragment fragmentHome;
    private City cityChosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        configureActionBar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (FrameLayout) findViewById(R.id.left_drawer);




        fragmentMenuLateral = new MenuLateralFragment();
        if(Utils.verifyConection(this)){
            serviceListCities();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.left_drawer, fragmentMenuLateral);
        transaction.commit();

        configureMenuLateral();

        updateDisplay(0);

        fragmentHome = new HomeFragment();
        trocaFragment(fragmentHome);
    }



    private void serviceListCities() {
        ListCitiesService serviceListCities = new ListCitiesService(this,this);
        serviceListCities.execute();
    }

    private void configureMenuLateral() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                // getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                // invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                // getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                // invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
    }

    @Override
    protected void onResume() {


        super.onResume();
    }




    private void updateDisplay(int position) {
        Fragment fragment = null;
        switch (position) {

            case 0:
                fragment = new HomeFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {


            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    public void openDrawer(){

        mDrawerLayout.openDrawer(mDrawerList);
    }







    @Override
    public void setTitle(CharSequence title) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void setCityChosen(City city) {
        fragmentHome.setCityChosen(city);
    }

    public void trocaFragment(Fragment fragmento) {

        if(mDrawerLayout.isShown()){
            mDrawerLayout.closeDrawers();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragmento);
        transaction.commit();
    }

    @Override
    public void onCitiesSuccess(ArrayList<City> cities) {
        fragmentMenuLateral.setCities(cities);
    }

    @Override
    public void onCitiesServerError() {

    }

    @Override
    public void onCitiesError(String error) {

    }



}
