package com.parohy.weatherapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.databinding.MainActivityBinding
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.SearchViewModel


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val searchViewModel = ViewModelProviders.of(
            this,
            application.getViewModelFactory()
        )[SearchViewModel::class.java]

        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
            .apply {
                lifecycleOwner = this@MainActivity
                searchModel = searchViewModel
            }

        searchViewModel.navigationObserver().observe(this, Observer {
            if (it) ResultActivity.start(this)
        })
    }
}
