package com.gpslocation;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.listeners.GooglePlayServiceLocationUpdateListener;

public class GooglePlayServiceLocationUpdate implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener {

	private static final int MILLISECONDS_PER_SECOND = 1000;
	public static final int UPDATE_INTERVAL_IN_SECONDS = 10;
	private static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
	private static final int FASTEST_INTERVAL_IN_SECONDS = 10;
	private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

	private LocationRequest mLocationRequest;

	private Activity activity;
	private GooglePlayServiceLocationUpdateListener googlePlayListener;
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private LocationClient locationClient;

	public GooglePlayServiceLocationUpdate(Activity activity, GooglePlayServiceLocationUpdateListener googlePlayListener) {
		this.activity = activity;
		this.googlePlayListener = googlePlayListener;

	}

	public void iniciarBusca() {
		mLocationRequest = LocationRequest.create();
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		mLocationRequest.setInterval(UPDATE_INTERVAL);
		mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

		locationClient = new LocationClient(activity, this, this);
		locationClient.connect();
	}

	public boolean isDisponivel() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
		if (ConnectionResult.SUCCESS == resultCode) {
            return true;
        } 
		
        return false;
	}
	
	public void disconectar() {
		try{
			locationClient.disconnect();
		} catch(Exception e){ }
	}
	
	@Override
	public void onLocationChanged(Location location) {
		googlePlayListener.onLocationUpdate(location);

	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
			try {
				connectionResult.startResolutionForResult(activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);

			} catch (IntentSender.SendIntentException e) {
				e.printStackTrace();
			}
		} else {
			googlePlayListener.onLocationUpdateFalhaAoConectar();
		}
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		locationClient.requestLocationUpdates(mLocationRequest, this);
	}

	@Override
	public void onDisconnected() { }

}
