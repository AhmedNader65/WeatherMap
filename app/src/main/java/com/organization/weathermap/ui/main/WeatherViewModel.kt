package com.organization.weathermap.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.organization.weathermap.domain.model.CityNotFoundException
import com.organization.weathermap.domain.model.NetworkException
import com.organization.weathermap.domain.model.NetworkUnavailableException
import com.organization.weathermap.domain.usecases.FetchWeather
import com.organization.weathermap.domain.usecases.GetWeather
import com.organization.weathermap.ui.model.ForecastUI
import com.organization.weathermap.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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

    private val _notAccurate = MutableStateFlow(false)
    val notAccurate: StateFlow<Boolean> = _notAccurate.asStateFlow()

    private val _error = MutableStateFlow(Pair(false, ""))
    val error: StateFlow<Pair<Boolean, String>> = _error.asStateFlow()


    fun getWeatherById(city: String) {
        viewModelScope.launch(mainDispatcher) {
            _forecasts.emit(null)
            try {
                fetchWeather(city)
                getFromCache(city)
            } catch (e: NetworkUnavailableException) {
                _notAccurate.emit(true)
                getFromCache(city)
            } catch (e: CityNotFoundException) {
                _error.emit(Pair(true, "City not found"))
            } catch (e: NetworkException) {
                _notAccurate.emit(true)
                getFromCache(city)
            } catch (e: Exception) {
                _error.emit(Pair(true, "Error fetching data"))
                e.printStackTrace()
                Logger.d("error")
            }
        }
    }

    private suspend fun getFromCache(city: String) {
        try {
            getWeather(city).map { forecast ->
                forecast.weather.map {
                    ForecastUI.fromDomain(it)
                }
            }.collect {
                onForecastReceived(it)
            }
        } catch (e: Exception) {
            _error.emit(Pair(true, "Error fetching data"))

        }
    }


    private suspend fun onForecastReceived(uiList: List<ForecastUI>) {
        _forecasts.emit(uiList)
    }

}