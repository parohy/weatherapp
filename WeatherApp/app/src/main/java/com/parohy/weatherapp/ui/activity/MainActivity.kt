package com.parohy.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.getAppComponent
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.WeatherViewModel
import com.parohy.weatherapp.value
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        weatherViewModel = ViewModelProviders.of(
            this,
            application.getViewModelFactory()
        )[WeatherViewModel::class.java]

        weatherViewModel.observe().observe(this, Observer {
            when {
                it.isSuccessful() -> ResultActivity.start(this)
                it.isLoading() -> Log.d(TAG, "TODO: Loading...")
                it.error != null -> Log.d(TAG, "Failed because of: ${it.error}")
            }
        })

        submitButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (validateField()) {
            weatherViewModel.getWeather(submitField.value())
        }
    }

    private fun validateField(): Boolean {
        submitField.error = null
        if (!submitField.value().matches(Regex("[a-zA-Z ]+"))) {
            submitField.error = "Invalid input"
            return false
        }
        return true
    }
}
