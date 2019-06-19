package com.parohy.weatherapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.WeatherViewModel
import com.parohy.weatherapp.value
import kotlinx.android.synthetic.main.main_activity.*

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

        weatherViewModel.get().observe(this, Observer {
            when {
                it.isLoading() -> Log.d(TAG, "Loading...")
                !it.isStale() && !it.isSuccessful() -> inputError.text = getString(R.string.failed)
                else -> ResultActivity.start(this)
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
        inputError.text = ""
        if (!submitField.value().matches(Regex("[a-zA-Z ]+"))) {
            val errorMessage = getString(R.string.invalid_input)
            submitField.error = errorMessage
            inputError.text = errorMessage
            return false
        }
        return true
    }
}
