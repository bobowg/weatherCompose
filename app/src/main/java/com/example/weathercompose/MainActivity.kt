package com.example.weathercompose

import android.app.AlertDialog
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
import com.example.weathercompose.data.getCityData
import com.example.weathercompose.data.getNowData
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.location
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.screen.DialogSearch
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
                val citylist = remember {
                    mutableStateOf(location("", "", "", ""))
                }
                val dialogState = remember { mutableStateOf(false) }
                if (dialogState.value) {
                    DialogSearch(dialogState, onSubmit = {
                        getCityData(it, this, citylist)
                        get24HourlyData(this, hourlyList)
                        get7DaysData(this, daysList)
                        getNowData(context = this, now = nowList)
                    })
                } else {
                    if (citylist.value.id.isNotEmpty()) {
                        getNowData(
                            context = this,
                            now = nowList,
                            id = citylist.value.id.toString()
                        )
                        get24HourlyData(this, hourlyList, id = citylist.value.id.toString())
                        get7DaysData(
                            context = this,
                            daysList = daysList,
                            id = citylist.value.id.toString()
                        )
                    } else {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                        builder.setMessage("城市ID不能为空").setTitle("出错了")
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }

                }
                Image(
                    painter = painterResource(id = R.drawable.sunny_bg),
                    contentDescription = "cloudy_bg",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard(
                        nowList,
                        onClickSync = {
                            getNowData(context = this@MainActivity, now = nowList)
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





