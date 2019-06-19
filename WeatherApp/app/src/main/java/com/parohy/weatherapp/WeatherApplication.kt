package com.parohy.weatherapp

import android.app.Application
import com.parohy.weatherapp.api.dagger.AppComponent
import com.parohy.weatherapp.api.dagger.DaggerAppComponent
import com.parohy.weatherapp.ui.viewmodel.ViewModelFactory
import javax.inject.Inject

class WeatherApplication: Application() {
    companion object {
        val TAG: String = WeatherApplication::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
    }

    fun getAppComponent(): AppComponent = appComponent

    fun getFactory(): ViewModelFactory = viewModelFactory
}