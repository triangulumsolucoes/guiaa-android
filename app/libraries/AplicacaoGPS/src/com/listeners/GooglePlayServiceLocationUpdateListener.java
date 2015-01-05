package com.listeners;

import android.location.Location;

public interface GooglePlayServiceLocationUpdateListener {
	public void onLocationUpdateFalhaAoConectar();

	public void onLocationUpdateConectado();

	public void onLocationUpdateDisconectado();

	public void onLocationUpdate(Location location);

	public void onLocationUpdateError();
}
