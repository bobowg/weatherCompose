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
import com.example.weathercompose.data.get7DaysData
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.now
import com.example.weathercompose.screen.MainCard
import com.example.weathercompose.screen.TabLayout
import com.example.weathercompose.ui.theme.WeatherComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherComposeTheme {
                val daysList = remember {
                    mutableStateOf(listOf<daily>())
                }
                val nowList = remember {
                    mutableStateOf(now("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
                }
                get7DaysData(this, daysList)
                getNowData(context = this, now = nowList)
                Image(
                    painter = painterResource(id = R.drawable.cloudy_bg),
                    contentDescription = "cloudy_bg",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard(nowList)
                    TabLayout(daysList)
                }
            }
        }
    }
}





