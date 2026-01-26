package com.zd.study.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {
    val weatherData = MutableLiveData<String>()

    fun getWeatherData(): MutableLiveData<String>{
        return weatherData
    }

    fun fetchWeatherData(){
        weatherData.value = "36.5摄氏度"
    }
}