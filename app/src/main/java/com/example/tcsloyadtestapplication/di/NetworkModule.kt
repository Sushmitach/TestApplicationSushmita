package com.example.tcsloyadtestapplication.di

import com.example.tcsloyadtestapplication.retrofit.CountriesApi
import com.example.tcsloyadtestapplication.utils.Constans
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCountriesApi(retrofit: Retrofit) : CountriesApi{
        return retrofit.create(CountriesApi::class.java)
    }

}