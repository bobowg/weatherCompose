package com.example.weathercompose.uitl

import androidx.annotation.DrawableRes
import com.example.weathercompose.R
import com.example.weathercompose.data.season
import com.example.weathercompose.data.season.Autumn
import com.example.weathercompose.data.season.Spring
import com.example.weathercompose.data.season.Summer
import com.example.weathercompose.data.season.Winter

fun toTime(date: String): String {
    val item = date.dropLast(6).replace("T", " ")
    return item
}

@DrawableRes
fun ImageReslut(): Int {
    val son: season = season.entries.random()
    return when (son) {
        Spring -> R.drawable.cloudy_bg
        Summer -> R.drawable.sunny_bg
        Autumn -> R.drawable.haze_bg
        Winter -> R.drawable.snow_bg
    }
}