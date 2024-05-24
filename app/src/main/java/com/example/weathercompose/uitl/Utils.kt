package com.example.weathercompose.uitl

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices

@Composable
@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)

fun CurrentLocationContent(): String {
    val context = LocalContext.current
    val locationClient = LocationServices.getFusedLocationProviderClient(context)
    var locationInfo = "三亚"
    locationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            locationInfo = "${location.latitude},${location.longitude}"
        }
    }
    return locationInfo
}