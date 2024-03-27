package com.example.tcsloyadtestapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.tcsloyadtestapplication.viewModel.MainViewModel
import com.example.tcsloyadtestapplication.viewModel.MainViewModelFactory
import net.cocooncreations.countriesmvvm.model.Country
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as CountriesApplicationClass).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.countriesLiveData.observe(this, {
            setContent {
                DisplayCountryListAndInfo(it)
            }
        })
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun DisplayCountryListAndInfo(countries: List<Country>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Country Information",
                            style = MaterialTheme.typography.h3
                        )
                    }
                }
                items(countries) { country ->
                    DisplayCard(
                        country.countryName!!,
                        country.capital!!,
                        country.population!!,
                        country.flag!!
                    )
                }
            }
        }
    }

    @Composable
    fun DisplayCard(name: String, capital: String, population: Int, image: String) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Flag",
                    modifier = Modifier
                        .size(130.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                    )
                    Text(
                        text = "Capital: " + capital,
                        style = MaterialTheme.typography.body2,
                    )
                    Text(
                        text = "Population: " + population,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }
}
