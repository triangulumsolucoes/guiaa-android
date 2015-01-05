package com.gpslocation;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.listeners.GooglePlayServiceLocationListener;

public class GooglePlayServiceLocation implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

	private Activity activity;
	private Context context;
	private GooglePlayServiceLocationListener googlePlayListener;
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private LocationClient locationClient;
	private Location mCurrentLocation;
	
	public GooglePlayServiceLocation(Activity activity, GooglePlayServiceLocationListener googlePlayListener) {
		this.activity = activity;
		this.googlePlayListener = googlePlayListener;
		
	}
	
	public GooglePlayServiceLocation(Context context, GooglePlayServiceLocationListener googlePlayListener) {
		this.context = context;
		this.googlePlayListener = googlePlayListener;
		
	}

	public void iniciarBusca() {
		
		if(activity == null){
			locationClient = new LocationClient(context, this, this);
		} else {
			locationClient = new LocationClient(activity, this, this);	
		}
		
		locationClient.connect();
	}
	
	public boolean isDisponivel() {
		int resultCode = 0;
		if(activity != null){
			resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
		}else {
			resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);	
		}
		
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
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
			try {
				if(activity != null) {
					connectionResult.startResolutionForResult(activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
				}
			} catch (IntentSender.SendIntentException e) {
				e.printStackTrace();
			}
		} else {
			googlePlayListener.onFalhaAoConectar();
		}
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		mCurrentLocation = locationClient.getLastLocation();

		if (mCurrentLocation != null) {
			googlePlayListener.onLocationSuccess(mCurrentLocation);
			
		} else {
			googlePlayListener.onLocationError();
		}
		locationClient.disconnect();
	}

	@Override
	public void onDisconnected() { }
}