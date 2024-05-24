package com.example.weathercompose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.get24HourlyData
import com.example.weathercompose.data.get7DaysData
import com.example.weathercompose.data.getCityData
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.location
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.screen.DialogSearch
import com.example.weathercompose.ui.screen.MainCard
import com.example.weathercompose.ui.screen.TabLayout
import com.example.weathercompose.ui.theme.WeatherComposeTheme
import com.example.weathercompose.uitl.CurrentLocationContent
import com.example.weathercompose.uitl.ImageReslut


class MainActivity : ComponentActivity() {


    @RequiresPermission(
        anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
    )
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
                val citylist = remember {
                    mutableStateOf(location("", "", "", ""))
                }
                val dialogState = remember { mutableStateOf(false) }
                if (dialogState.value) {
                    DialogSearch(dialogState, onSubmit = {
                        getCityData(it, this, citylist)
                    })
                } else {
                    if (citylist.value.id.isNotEmpty()) {
                        getNowData(
                            context = this,
                            now = nowList,
                            id = citylist.value.id
                        )
                        get24HourlyData(this, hourlyList, id = citylist.value.id)
                        get7DaysData(
                            context = this,
                            daysList = daysList,
                            id = citylist.value.id
                        )
                    } else {
                        get24HourlyData(this, hourlyList)
                        get7DaysData(this, daysList)
                        getNowData(context = this, now = nowList)
                    }
                }
                Image(
                    painter = painterResource(id = ImageReslut()),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.8f)
                ) {
                    MainCard(
                        nowList,
                        onClickSync = {

                                getCityData(CurrentLocationContent(), this@MainActivity, citylist)
                                getNowData(context = this@MainActivity, now = nowList)
                                get7DaysData(context = this@MainActivity, daysList = daysList)
                                get24HourlyData(context = this@MainActivity, daysList = hourlyList)
                        },
                        onClickSearch = {
                            dialogState.value = true
                        },
                        title = citylist.value.name
                    )
                    TabLayout(daysList, hourlyList)
                }

            }
        }
    }


}





