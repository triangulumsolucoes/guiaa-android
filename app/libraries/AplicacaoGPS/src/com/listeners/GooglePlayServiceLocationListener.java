package com.listeners;

import android.location.Location;

public interface GooglePlayServiceLocationListener {

	public void onFalhaAoConectar();

	public void onConectado();

	public void onDisconectado();

	public void onLocationSuccess(Location location);

	public void onLocationError();
	
}
