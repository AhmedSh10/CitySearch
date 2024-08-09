package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.City
import com.example.myapplication.repo.CitiesRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val repository: CitiesRepository) : ViewModel() {

    private val _filteredCities = MutableLiveData<List<City>>()
    val filteredCities: LiveData<List<City>> = _filteredCities

    init {
        _filteredCities.value = repository.loadCities().sortedBy { it.name }
    }

    fun filterCities(prefix: String) {
        val cities = repository.loadCities()
        _filteredCities.value = cities.filter { it.name.startsWith(prefix, ignoreCase = true) }.sortedBy { it.name }
    }
}
