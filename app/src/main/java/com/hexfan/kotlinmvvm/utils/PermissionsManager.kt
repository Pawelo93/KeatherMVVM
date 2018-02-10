package com.hexfan.kotlinmvvm.utils

import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.app.Activity
import android.content.Context


/**
 * Created by Pawel on 09.02.2018.
 */

object PermissionsManager{

    var callback: Callback? = null

    fun need(activity: Activity, permission: String, callback: Callback){
        need(activity, arrayOf(permission), callback)
    }

    fun need(activity: Activity, permissions: Array<out String>, callback: Callback){
        PermissionsManager.callback = callback
        val requestCode = 0

        if(hasPermissions(activity, permissions))
            callback.permissionGranted(requestCode)
        else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        if (verifyPermissions(grantResults)) {
            //Permission has been granted
            callback?.permissionGranted(requestCode);
        } else {
            callback?.permissionCanceled();
        }
    }

    fun hasPermission(context: Context, permission: String): Boolean {
        return hasPermissions(context, arrayOf(permission))
    }

    fun hasPermissions(context: Context, permissions: Array<out String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun verifyPermissions(grantResults: IntArray): Boolean{
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    interface Callback {
        fun permissionGranted(requestCode: Int)

        fun permissionCanceled()
    }

}