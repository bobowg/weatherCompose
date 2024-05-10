package com.example.weathercompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.vector.DefaultFillType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.weathercompose.ui.theme.BlueLight
import com.example.weathercompose.ui.theme.Pink80

@Composable
fun weatherSelectIcon(icon: String) {
    val svgLink = "https://icons.qweather.com/assets/icons/${icon}.svg"
    val context = LocalContext.current
    val imageLoader = ImageRequest.Builder(context)
        .data(svgLink)
        .decoderFactory(SvgDecoder.Factory())
        .build()
    AsyncImage(
        model = imageLoader,
        contentDescription = null,
        modifier = Modifier
            .size(35.dp)
            .padding(top = 3.dp, end = 8.dp)
            .background(color = BlueLight),
        filterQuality = DefaultFilterQuality,
        colorFilter = ColorFilter.tint(Pink80)
    )

}