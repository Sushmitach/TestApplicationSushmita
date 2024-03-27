package com.example.tcsloyadtestapplication.retrofit

import net.cocooncreations.countriesmvvm.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>
}