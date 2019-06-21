package com.parohy.weatherapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parohy.weatherapp.R
import com.parohy.weatherapp.databinding.MainActivityBinding
import com.parohy.weatherapp.getViewModelFactory
import com.parohy.weatherapp.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(){
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        searchViewModel = ViewModelProviders.of(
            this,
            application.getViewModelFactory()
        )[SearchViewModel::class.java]

        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
            .apply {
                lifecycleOwner = this@MainActivity
                searchModel = searchViewModel
            }

        searchViewModel.get().observe(this, Observer {
            when {
                it.isLoading() -> Log.d(TAG, "Loading...")
                !it.isStale() && !it.isSuccessful() -> inputError.visibility = View.VISIBLE
                else -> ResultActivity.start(this)
            }
        })
    }
}
