package com.example.weathercompose.uitl

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.weathercompose.R
import com.example.weathercompose.data.season
import com.example.weathercompose.data.season.Autumn
import com.example.weathercompose.data.season.Night
import com.example.weathercompose.data.season.Spring
import com.example.weathercompose.data.season.Summer
import com.example.weathercompose.data.season.Winter
import com.example.weathercompose.ui.theme.Pink80
import com.example.weathercompose.ui.theme.Purple40

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
        Night -> R.drawable.night_bg
    }
}

@Composable
fun textColor():Color{
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Purple40,
        targetValue = Pink80,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )
    return animatedColor
}