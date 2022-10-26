package com.organization.weathermap.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.organization.weathermap.ui.model.ForecastUI
import com.organization.weathermap.ui.theme.divider_color

const val WEATHER_LIST_TEST_TAG = "weather_list"

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val listState = rememberLazyListState()
        val forecastUIList by viewModel.forecasts.collectAsState()
        Column {
            SearchBox(onValueChanged = { viewModel.getWeatherById(it) })

            Box(modifier = Modifier) {

                forecastUIList?.let {
                    ForecastList(
                        it,
                        modifier = modifier.testTag(WEATHER_LIST_TEST_TAG),
                        listState = listState
                    )
                } ?: run {
                    Box(
                        modifier = Modifier.width(48.dp).align(Alignment.Center)
                            .height(48.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.testTag("myProgressIndicator"))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    onValueChanged: (city: String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }, placeholder = { Text("Search") },
        onValueChange = { newText ->
            text = newText
            onValueChanged(newText.text)

        }
    )
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
        modifier = modifier
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {

//        val painter = rememberAsyncImagePainter(
//            Builder(LocalContext.current).data(data = item.thumbnail)
//                .apply(block = fun Builder.() {
//                    crossfade(true)
//                }).placeholder(R.drawable.ic_launcher_background).build()
//        )
//        Image(
//            painter = painter,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//
//            modifier = Modifier.width(50.dp).height(50.dp).clip(RoundedCornerShape(8.dp)),
//        )
        Column(modifier = Modifier.padding(start = 12.dp)) {

            Text(
                text = item.date,
                style = MaterialTheme.typography.body1
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color.Gray,
                    text = item.weather,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    color = Color.Gray, text = item.date
                )
            }
        }

    }
}

@Preview
@Composable
fun ForecastItemPreview() {
    ForecastItem(
        ForecastUI(
            "15-10-2022 13:4",
            "Biden Pardons Thousands Convicted of Marijuana Possession Under Federal Law",
        ))
}