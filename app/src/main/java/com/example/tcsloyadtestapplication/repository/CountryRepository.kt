package com.example.tcsloyadtestapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tcsloyadtestapplication.retrofit.CountriesApi
import net.cocooncreations.countriesmvvm.model.Country
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountriesApi) {
  private val countries = MutableLiveData<List<Country>>()
      val countriesObserver: LiveData<List<Country>>
      get() = countries

    suspend fun getCountries(){
        val result = api.getCountries()
        if (result.isSuccessful && result.body() != null){
            countries.postValue(result.body())
        }
    }
}