package com.example.weathercompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercompose.R
import com.example.weathercompose.data.daily
import com.example.weathercompose.data.hourly
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.theme.BlueLight
import com.example.weathercompose.uitl.toTime
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@Composable
fun MainCard(
    nowList: MutableState<now>
) {
    Column(
        modifier = Modifier
            .padding(5.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.6f),
            colors = CardDefaults.cardColors(containerColor = BlueLight),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                            text = toTime(nowList.value.obsTime),
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        weatherSelectIcon(nowList.value.icon)

                    }
                    Text(
                        text = "三亚",
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Text(
                        text = "${nowList.value.temp}°C",
                        style = TextStyle(fontSize = 65.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Text(
                        text = nowList.value.text,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "查找",
                                tint = Color.White
                            )

                        }

                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "感觉好像${nowList.value.feelsLike}°C 风向：${nowList.value.windDir}级别：${nowList.value.windScale}",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                            color = Color.White
                        )

                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "查找",
                                tint = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabLayout(
    daysList: MutableState<List<daily>>,
    hourlyList: MutableState<List<hourly>>
) {
    val tabList = listOf("24小时", "7天")
    val pageState = rememberPagerState()
    val tabIndex = pageState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(5.dp)),
    ) {
        PrimaryTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.8f),
            selectedTabIndex = tabIndex,
            containerColor = BlueLight,
            contentColor = Color.White,
            divider = {

            }
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = text, color = Color.White) },
                )
            }
        }

        HorizontalPager(
            count = tabList.size,
            state = pageState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            when (index) {
                0 -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            hourlyList.value
                        ) { _, item ->
                            horlyList(item)
                        }
                    }
                }

                1 -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            daysList.value
                        ) { _, item ->
                            dailyItem(item)
                        }
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(
                            hourlyList.value
                        ) { _, item ->
                            horlyList(item)
                        }
                    }
                }
            }

        }
    }
}

