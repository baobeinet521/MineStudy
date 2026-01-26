package com.zd.study.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zd.study.viewmodel.WeatherViewModel

class ComposeTestActivity: AppCompatActivity() {
    private var mViewModel: WeatherViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this)[WeatherViewModel::class]
        }
    }
}