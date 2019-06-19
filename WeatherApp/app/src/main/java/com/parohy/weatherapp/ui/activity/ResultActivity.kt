package com.parohy.weatherapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.getAppComponent
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.ViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.WeatherViewModel
import javax.inject.Inject

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
        setContentView(R.layout.result_activity)
        
        weatherViewModel = ViewModelProviders.of(
            this,
            application.getViewModelFactory()
        )[WeatherViewModel::class.java]

        weatherViewModel.observe().observe(this, Observer {
            Log.d(TAG, "Result: $it")
        })
    }
}