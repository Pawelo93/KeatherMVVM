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
import io.reactivex.subjects.PublishSubject


/**
 * Created by Pawel on 10.02.2018.
 */

class ReactiveLocationProvider(val context: Context): LocationListener {


        var locations = MutableLiveData<Location>()
//    var locations = PublishSubject.create<Location>()
    private var locationManager: LocationManager
//    private var locationClient: FusedLocationProviderClient
    private var request: LocationRequest

//    var locationCallback: LocationCallback

    init {
        request = LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(1)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

//        locationClient = LocationServices.getFusedLocationProviderClient(context)
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult?) {
//
//                Timber.d("location ${locationResult!!.locations[0]}")
//                if(locationResult != null)
//                    locations.onNext(locationResult.locations[0])
////                for (location in locationResult!!.locations) {
////
////                }
//            }
//        }
//        locations.debounce(50, TimeUnit.MILLISECONDS)

    }

//    object locationCallback: LocationCallback(){
//        override fun onLocationResult(p0: LocationResult?) {
//
//        }
//    }

    fun loadLocation(){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100.toLong(), 0f, this)
            val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

//            locations.onNext(lastKnownLocation)
            locations.value = lastKnownLocation
        }
    }

    override fun onLocationChanged(location: Location?) {
//        if(location != null)
//            locations.onNext(location)
        locations.value = location

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}