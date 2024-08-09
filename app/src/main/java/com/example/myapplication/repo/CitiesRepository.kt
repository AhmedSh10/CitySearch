package com.example.myapplication.repo

import android.content.Context
import com.example.myapplication.models.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CitiesRepository @Inject constructor(@ApplicationContext private val context: Context) {

    fun loadCities(): List<City> {
        val inputStream = context.assets.open("cities.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(json, object : TypeToken<List<City>>() {}.type)
    }
}
