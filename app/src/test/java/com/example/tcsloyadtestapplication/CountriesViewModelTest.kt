package com.example.tcsloyadtestapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.tcsloyadtestapplication.repository.CountryRepository
import com.example.tcsloyadtestapplication.retrofit.CountriesApi
import com.example.tcsloyadtestapplication.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import net.cocooncreations.countriesmvvm.model.Country
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.io.IOException


@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    @Mock
    lateinit var api: CountriesApi

    @Mock
    lateinit var repository: CountryRepository

    @Mock
    lateinit var viewModel: MainViewModel

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainDispatcherRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = coroutineRule.testDispatcher

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCountries_isSuccess() = runTest {
        Response.success(Country("countryName", "capital", "url", 100))
        val countryApi = Mockito.mock(CountriesApi::class.java)
        countryApi.getCountries()
        Mockito.verify(countryApi).getCountries()
    }

    @Test
    fun getCountries_isFail() = runTest {
        whenever(repository.getCountries()) doAnswer {
            throw IOException()
        }
        val countryApi = Mockito.mock(CountriesApi::class.java)
        countryApi.getCountries()
        Assert.fail(countryApi.getCountries().errorBody().toString())
    }
}