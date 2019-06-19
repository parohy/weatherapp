package com.parohy.weatherapp.api.dagger

import com.parohy.weatherapp.WeatherApplication
import com.parohy.weatherapp.ui.activity.MainActivity
import com.parohy.weatherapp.ui.activity.ResultActivity
import com.parohy.weatherapp.ui.viewmodel.ViewModelModule
import com.parohy.weatherapp.ui.viewmodel.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {
    fun inject(app: WeatherApplication)
    fun inject(model: WeatherViewModel)
}