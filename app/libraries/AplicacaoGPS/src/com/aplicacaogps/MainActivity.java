package com.aplicacaogps;

import com.gpslocation.GooglePlayServiceLocation;
import com.gpslocation.GooglePlayServiceLocationUpdate;
import com.listeners.GooglePlayServiceLocationListener;
import com.listeners.GooglePlayServiceLocationUpdateListener;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements GooglePlayServiceLocationListener, GooglePlayServiceLocationUpdateListener {

	private GooglePlayServiceLocation googlePlayServiceLocation;
	private GooglePlayServiceLocationUpdate googlePlayServiceLocationUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		googlePlayServiceLocation = new GooglePlayServiceLocation(this, this);
		googlePlayServiceLocationUpdate = new GooglePlayServiceLocationUpdate(this, this);
		
//		if(googlePlayServiceLocation.isDisponivel()) {
//			googlePlayServiceLocation.iniciarBusca();
//			
//		} else {
//		}
		
		if(googlePlayServiceLocationUpdate.isDisponivel()) {
			googlePlayServiceLocationUpdate.iniciarBusca();
		} else {
		}
	}

	@Override
	protected void onStop() {
		googlePlayServiceLocation.disconectar();
		googlePlayServiceLocationUpdate.disconectar();
		super.onStop();
	}
	
	@Override
	public void onFalhaAoConectar() {
		Log.d("FALHA AO CONECTAR", "FALHA AO CONECTAR");
		
	}

	@Override
	public void onConectado() {
		Log.d("CONECTADO", "CONECTADO");
	}

	@Override
	public void onDisconectado() {
		Log.d("DISCONECTADO", "DISCONECTADO");
	}

	@Override
	public void onLocationSuccess(Location location) {
		Log.d("ON LOCATION SUCCESS", "ON LOCATION SUCCESS");
		Log.d("LATITUDe", "LATITUDE: " + location.getLatitude());
		Log.d("LONGITUDE", "LONGITUDE: " + location.getLongitude());
	}

	@Override
	public void onLocationError() {
		Log.d("ON LOCATION ERROR", "ON LOCATION ERROR");
	}

	@Override
	public void onLocationUpdateFalhaAoConectar() {
		Log.d("ON LOCATION UPDATE", "ON LOCATION UPDATE FALHA");
	}

	@Override
	public void onLocationUpdateConectado() {
		Log.d("ON LOCATION UPDATE CONECTADO", "ON LOCATION UPDATE CONECTADO");
	}

	@Override
	public void onLocationUpdateDisconectado() {
		Log.d("ON LOCATION UPDATE DISCONECTADO", "ON LOCATION UPDATE DISCONECTADO");
	}

	@Override
	public void onLocationUpdate(Location location) {
		Log.d("ON LOCATION UPDATE", "ON LOCATION UPDATE");
		Log.d("LATITUDE", "LATITUDE: " + location.getLatitude());
		Log.d("LONGITUDE", "LONGITUDE: " + location.getLongitude());
	}

	@Override
	public void onLocationUpdateError() {
		Log.d("ON LOCATION UPDATE ERROR", "ON LOCATION UPDATE ERROR");
	}
}
