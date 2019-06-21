package com.parohy.weatherapp.ui.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.parohy.weatherapp.R
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.api.model.Weather
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    companion object {
        val TAG: String = SearchViewModel::class.java.simpleName

        @BindingAdapter("isError")
        @JvmStatic
        fun setError(view: EditText, error: Boolean) {
            view.error = if (error) view.context.getString(R.string.invalid_input) else null
        }

        @BindingAdapter("loading")
        @JvmStatic
        fun loading(view: ProgressBar, loading: Boolean) {
            view.visibility = if (loading) View.VISIBLE else View.GONE
        }

        @BindingAdapter("showError")
        @JvmStatic
        fun showRemoteError(view: TextView, error: Boolean) {
            view.visibility = if (error) View.VISIBLE else View.GONE
        }
    }

    private val weatherData: LiveData<Resource<Weather>> = weatherRepository.observeRepoData()
    val loading: LiveData<Boolean> = Transformations.map(weatherData) {
        it.isLoading()
    }
    val remoteError: LiveData<Boolean> = Transformations.map(weatherData) {
        !it.isStale() && !it.isSuccessful() && !it.isLoading()
    }

    val searchWord: MutableLiveData<String> = MutableLiveData()
    private val fieldError: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean>
        get() = fieldError

    fun search() {
        if (validateField()) {
            weatherRepository.getWeather(searchWord.value!!)
        }
    }

    fun navigationObserver(): LiveData<Boolean> = Transformations.map(weatherData) { it.isSuccessful() }

    private fun validateField(): Boolean {
        if (searchWord.value?.matches(Regex("[a-zA-Z ]+")) != true) {
            fieldError.value = true
            return false
        }
        fieldError.value = false
        return true
    }
}