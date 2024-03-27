package com.example.tcsloyadtestapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcsloyadtestapplication.repository.CountryRepository
import kotlinx.coroutines.launch
import net.cocooncreations.countriesmvvm.model.Country

class MainViewModel(private val repository: CountryRepository) : ViewModel() {
    val countriesLiveData: LiveData<List<Country>>
        get() = repository.countriesObserver

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {
            repository.getCountries()
        }
    }


}