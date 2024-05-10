package com.example.weathercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.get24HourlyData
import com.example.weathercompose.data.get7DaysData
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.screen.MainCard
import com.example.weathercompose.ui.screen.TabLayout
import com.example.weathercompose.ui.theme.WeatherComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherComposeTheme {
                val daysList = remember {
                    mutableStateOf(listOf<daily>())
                }
                val hourlyList = remember {
                    mutableStateOf(listOf<hourly>())
                }
                val nowList = remember {
                    mutableStateOf(now("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
                }
                get24HourlyData(this,hourlyList)
                get7DaysData(this, daysList)
                getNowData(context = this, now = nowList)
                Image(
                    painter = painterResource(id = R.drawable.sunny_bg),
                    contentDescription = "cloudy_bg",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard(nowList)
                    TabLayout(daysList,hourlyList)
                }
            }
        }
    }
}





