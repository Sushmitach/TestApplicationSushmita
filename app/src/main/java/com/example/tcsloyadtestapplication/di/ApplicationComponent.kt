package com.example.tcsloyadtestapplication.di

import com.example.tcsloyadtestapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}