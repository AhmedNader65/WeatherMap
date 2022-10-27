package com.organization.weathermap.ui.main

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.organization.weathermap.R
import com.organization.weathermap.ui.model.ForecastUI
import com.organization.weathermap.ui.theme.divider_color

const val WEATHER_LIST_TEST_TAG = "weather_list"

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    time: Long = System.currentTimeMillis() / 1000,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), color = MaterialTheme.colors.background
    ) {
        val listState = rememberLazyListState()
        var isLoading by rememberSaveable { mutableStateOf(false) }
        val forecastUIList by viewModel.forecasts.collectAsState()
        val isNotAccurate by viewModel.notAccurate.collectAsState()
        val error by viewModel.error.collectAsState()
        Column {
            SearchBox(onValueChanged = {
                viewModel.getWeatherById(it)
                isLoading = true
            })

            Box(modifier = Modifier) {

                forecastUIList?.let {
                    isLoading = false
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        val currentForecast = it.first { it.date > time }
                        if (isNotAccurate)
                            Text(
                                text = "This data might not be accurate",
                                style = TextStyle(fontSize = 22.sp, color = Color.Red)
                            )
                        Text(
                            text = currentForecast.temp + "°C",
                            style = TextStyle(fontSize = 48.sp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "http://openweathermap.org/img/wn/${currentForecast.icon}@2x.png")
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                    }).placeholder(R.drawable.ic_launcher_background).build()
                            )
                            Image(
                                painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,

                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                            )
                            Text(
                                currentForecast.weather
                            )
                        }
                        ForecastList(
                            it,
                            modifier = modifier.testTag(WEATHER_LIST_TEST_TAG),
                            listState = listState
                        )
                    }
                } ?: run {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(48.dp)
                    ) {
                        if (isLoading)
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(48.dp)
                                    .testTag("myProgressIndicator")
                            )

                        if (error.first) {
                            isLoading = false
                            Text(
                                text = error.second,
                                style = TextStyle(fontSize = 22.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier, onValueChanged: (city: String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(value = text,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = { Text("Enter city name...") },
            onValueChange = { newText ->
                text = newText
            })
        Button({ onValueChanged(text.text) }, content = {
            Text("Search")
        })
    }
}

@Composable
fun ForecastList(
    forecastUIList: List<ForecastUI>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(forecastUIList) { item ->
            Column(Modifier.fillParentMaxWidth()) {
                ForecastItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = item,
                )
                Divider(color = divider_color)
            }
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun ForecastItem(
    item: ForecastUI,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = item.getRelativeDate(), style = MaterialTheme.typography.h6
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "http://openweathermap.org/img/wn/${item.icon}@2x.png")
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).placeholder(R.drawable.ic_launcher_background).build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    )
                    Text(
                        item.weather
                    )
                }
            }

            Text(
                text = item.temp + "°C",
                style = TextStyle(fontSize = 36.sp)
            )
        }
    }
}

@Preview
@Composable
fun ForecastItemPreview() {
    ForecastItem(
        ForecastUI(
            "15-10-2022 13:4",
            1234546L,
            "",
            "",
            "Biden Pardons Thousands Convicted of Marijuana Possession Under Federal Law",
        )
    )
}