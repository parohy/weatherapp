package com.parohy.weatherapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.parohy.weatherapp.api.model.Weather
import com.parohy.weatherapp.api.rest.Remote
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val remote: Remote) {
    companion object {
        val TAG: String = WeatherRepository::class.java.simpleName
    }

    private val data: MediatorLiveData<Resource<Weather>> = MediatorLiveData()

    fun getWeather(city: String) {
        val remoteData = remote.weatherInCity(city)
        data.addSource(remoteData) {
            data.value = it
            //TODO: If there were persistence or DB pass result and return from DB as single truth
        }
    }

    fun getWeatherXStreams(city: String) {
        val remoteData = LiveDataReactiveStreams.fromPublisher(remote.weatherInCityRX(city))
        data.addSource(remoteData) {
            data.value = Resource.success(it)
            //TODO: If there were persistence or DB pass result and return from DB as single truth
        }
    }

    fun getWeatherRX(city: String) {
        var disposable: Disposable? = null
        val action = Action {
            disposable?.dispose()
        }

        disposable = remote.weatherInCityRX(city)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                data.value = Resource.loading()
            }
            .doOnComplete(action)
            .subscribe(
                { weather ->
                    data.value = Resource.success(weather)
                },
                { throwable ->
                    data.value = Resource.error(throwable)
                })
    }

    fun observeRepoData(): LiveData<Resource<Weather>> = data
}