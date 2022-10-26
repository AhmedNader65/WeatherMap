package com.organization.weathermap.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.organization.weathermap.domain.model.NetworkException
import com.organization.weathermap.domain.model.NetworkUnavailableException
import com.organization.weathermap.domain.usecases.FetchWeather
import com.organization.weathermap.domain.usecases.GetWeather
import com.organization.weathermap.ui.model.ForecastUI
import com.organization.weathermap.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetWeather,
    private val fetchWeather: FetchWeather,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _forecasts = MutableStateFlow<List<ForecastUI>?>(null)
    val forecasts: StateFlow<List<ForecastUI>?> = _forecasts.asStateFlow()


    fun getWeatherById(city: String) {
        viewModelScope.launch(mainDispatcher) {
            try {
                fetchWeather(city)
                getFromCache(city)
            } catch (e: Exception) {
                Logger.d("error")
            } catch (e: NetworkUnavailableException) {
                getFromCache(city)
            } catch (e: NetworkException) {
                getFromCache(city)
            }
        }
    }

    private suspend fun getFromCache(city: String) {
        getWeather(city).map { forecast ->
            forecast.weather.map {
                ForecastUI.fromDomain(it)
            }
        }.collect {
            onForecastReceived(it)
        }
    }


    private suspend fun onForecastReceived(uiList: List<ForecastUI>) {
        _forecasts.emit(uiList)
    }

}