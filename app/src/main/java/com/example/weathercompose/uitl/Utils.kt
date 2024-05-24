package com.example.weathercompose.uitl

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.location.LocationServices

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(context: Context): String {
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember { mutableStateOf("") }
    locationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            locationInfo = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
        }else{
            locationInfo = "三亚"
        }
    }
    return locationInfo
}