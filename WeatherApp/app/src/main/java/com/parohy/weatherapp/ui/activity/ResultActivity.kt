package com.parohy.weatherapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.databinding.ResultActivityBinding
import com.parohy.weatherapp.getAppComponent
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.WeatherViewModel

class ResultActivity: AppCompatActivity() {
    companion object {
        val TAG: String = ResultActivity::class.java.simpleName

        fun start(from: Context) {
            from.startActivity(Intent(from, ResultActivity::class.java))
        }
    }

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel = ViewModelProviders.of(
            this,
            application.getViewModelFactory()
        )[WeatherViewModel::class.java]

        application.getAppComponent().inject(weatherViewModel)

        DataBindingUtil.setContentView<ResultActivityBinding>(this, R.layout.result_activity)
            .apply {
                lifecycleOwner = this@ResultActivity
                weatherModel = weatherViewModel
            }
    }
}