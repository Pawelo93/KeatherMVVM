package com.hexfan.kotlinmvvm.utils


import android.Manifest
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.LocationRequest

class ReactiveLocationProvider(val context: Context) : LocationListener {

    var locations = MutableLiveData<Location>()
    private var locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var request: LocationRequest = LocationRequest.create() //standard GMS LocationRequest
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setNumUpdates(5)
            .setInterval(100)
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)

    fun loadLocation() {
        if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000.toLong(), 0f, this)
            val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            locations.value = lastKnownLocation
        }
    }

    override fun onLocationChanged(location: Location?) {
        locations.value = location

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}