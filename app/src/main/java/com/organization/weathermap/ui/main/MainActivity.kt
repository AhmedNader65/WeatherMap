package com.organization.weathermap.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.organization.weathermap.ui.theme.BasicStateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicStateTheme {
                // A surface container using the 'background' color from the theme
                myApp()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun myApp() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        scaffoldState = scaffoldState,
        content = { innerPadding ->
            WeatherScreen(modifier = Modifier.padding(innerPadding))
        })
}
