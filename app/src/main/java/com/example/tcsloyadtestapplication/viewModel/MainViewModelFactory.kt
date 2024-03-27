package com.example.tcsloyadtestapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tcsloyadtestapplication.repository.CountryRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private  val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}