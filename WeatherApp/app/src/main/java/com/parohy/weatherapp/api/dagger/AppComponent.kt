package com.parohy.weatherapp.api.dagger

import com.parohy.weatherapp.ui.activity.MainActivity
import com.parohy.weatherapp.ui.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}