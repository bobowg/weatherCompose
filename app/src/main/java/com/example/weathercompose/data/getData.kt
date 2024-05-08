package com.example.weathercompose.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


const val API_KEY = "b766df9be8b148dca6717290db69a739"


fun getNowData(context: Context, now: MutableState<now>) {
    val url = "https://devapi.qweather.com/v7/weather/now?location=" +
            "101310218" +
            "&key=$API_KEY"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val list = getWeather(response)
            now.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        }
    )
    queue.add(sRequest)
}

fun getWeather(response: String): now {
    val mainObject = JSONObject(response)
    val item = mainObject.getJSONObject("now")
    return now(
        obsTime = item.getString("obsTime"),
        temp = item.getString("temp"),
        feelsLike = item.getString("feelsLike"),
        icon = item.getString("icon"),
        text = item.getString("text"),
        wind360 = item.getString("wind360"),
        windDir = item.getString("windDir"),
        windScale = item.getString("windScale"),
        windSpeed = item.getString("windSpeed"),
        humidity = item.getString("humidity"),
        precip = item.getString("precip"),
        pressure = item.getString("pressure"),
        vis = item.getString("vis"),
        cloud = item.getString("cloud"),
        dew = item.getString("dew")
    )


}

fun get7DaysData(context: Context, daysList: MutableState<List<daily>>) {
    val url = "https://devapi.qweather.com/v7/weather/7d?location=" +
            "101310218" +
            "&key=$API_KEY"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val list = getWeatherBy7Days(response)
            daysList.value = list
        }, {
            Log.d("mylog", "VolleyError:$it")
        }
    )
    queue.add(sRequest)
}


fun getWeatherBy7Days(response: String): List<daily> {
    if (response.isEmpty()) return listOf()
    val list = ArrayList<daily>()
    val mainObject = JSONObject(response)
    val days = mainObject.getJSONArray("daily")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        list.add(
            daily(
                fxDate = item.getString("fxDate"),
                sunrise = item.getString("sunrise"),
                sunset = item.getString("sunset"),
                moonrise = item.getString("moonrise"),
                moonset = item.getString("moonset"),
                moonPhase = item.getString("moonPhase"),
                moonPhaseIcon = item.getString("moonPhaseIcon"),
                tempMax = item.getString("tempMax"),
                tempMin = item.getString("tempMin"),
                iconDay = item.getString("iconDay"),
                textDay = item.getString("textDay"),
                iconNight = item.getString("iconNight"),
                textNight = item.getString("textNight"),
                wind360Day = item.getString("wind360Day"),
                windDirDay = item.getString("windDirDay"),
                windScaleDay = item.getString("windScaleDay"),
                windSpeedDay = item.getString("windSpeedDay"),
                wind360Night = item.getString("wind360Night"),
                windDirNight = item.getString("windDirNight"),
                windScaleNight = item.getString("windScaleNight"),
                windSpeedNight = item.getString("windSpeedNight"),
                humidity = item.getString("humidity"),
                precip = item.getString("precip"),
                pressure = item.getString("pressure"),
                vis = item.getString("vis"),
                cloud = item.getString("cloud"),
                uvIndex = item.getString("uvIndex"),
            )
        )
    }
    return list
}