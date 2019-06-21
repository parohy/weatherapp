package com.parohy.weatherapp.ui.viewmodel

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    }

    var searchWord: MutableLiveData<String> = MutableLiveData()

    private val fieldError: MutableLiveData<Boolean> = MutableLiveData()

    val error: LiveData<Boolean>
        get() = fieldError

    fun search() {
        if (validateField()) {
            weatherRepository.getWeather(searchWord.value!!)
        }
    }

    fun get(): LiveData<Resource<Weather>> = weatherRepository.observeRepoData()

    private fun validateField(): Boolean {
        if (searchWord.value?.matches(Regex("[a-zA-Z ]+")) != true) {
            fieldError.value = true
            return false
        }
        fieldError.value = false
        return true
    }
}