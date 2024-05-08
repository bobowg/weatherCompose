package com.example.weathercompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercompose.R
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.theme.BlueLight



@Composable
fun ListItem(item:daily) {
    Card(
        modifier = Modifier
            .fillMaxWidth().alpha(0.5f)
            .padding(top = 3.dp),
        colors = CardDefaults.cardColors(containerColor = BlueLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp),
            ) {
                Text(text = item.fxDate)
                Text(text = item.textDay, color = Color.White)
            }

            Text(text = "${item.tempMax}°C", color = Color.White, style = TextStyle(fontSize = 25.sp))
            Image(
                painter = painterResource(id = R.drawable.ic_cloud_zappy),
                contentDescription = "晴",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(35.dp)
            )

        }
    }
}