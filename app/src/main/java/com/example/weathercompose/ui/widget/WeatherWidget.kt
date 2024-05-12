package com.example.weathercompose.ui.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.weathercompose.MainActivity
import com.example.weathercompose.R


class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {

    // Let MyAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = WeatherWidget()
}

val countKey = intPreferencesKey("count")

class WeatherWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WeatherNow()
            }
        }
    }


    @Composable
    fun WeatherNow() {
        val count = currentState(key = countKey) ?: 0
        Column(
            modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sumit${count}",
            )
            Button(text = "加法", onClick = actionRunCallback(RefreshAction::class.java))
        }
    }

}


class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prsf ->
            val current = prsf[countKey]
            if (current != null) {
                prsf[countKey] = current + 1
            }else{
                prsf[countKey] = 1
            }
        }
        WeatherWidget().update(context,glanceId)

    }

}