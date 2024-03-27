package com.example.tcsloyadtestapplication

import android.app.Application
import com.example.tcsloyadtestapplication.di.ApplicationComponent
import com.example.tcsloyadtestapplication.di.DaggerApplicationComponent


class CountriesApplicationClass : Application() {
  lateinit var applicationComponent: ApplicationComponent
   override fun onCreate() {
        super.onCreate()
    applicationComponent =  DaggerApplicationComponent.builder().build()

    }
}