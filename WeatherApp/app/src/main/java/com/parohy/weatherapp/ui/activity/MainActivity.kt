package com.parohy.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.parohy.weatherapp.R
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.getAppComponent
import com.parohy.weatherapp.value
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        application.getAppComponent().inject(this)
        submitButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (validateField()) {
            weatherRepository.getWeather(submitField.value())
            ResultActivity.start(this)
        }
    }

    private fun validateField(): Boolean {
        submitField.error = ""
        if (!submitField.value().matches(Regex("[a-zA-Z ]+"))) {
            submitField.error = "Invalid input"
            return false
        }
        return true
    }
}
