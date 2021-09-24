package com.realluck.roomwordsample.db

import androidx.annotation.WorkerThread
import com.realluck.roomwordsample.model.City
import kotlinx.coroutines.flow.Flow

class CityRepository(private val cityDao: CityDao)
{
    fun allWords(acs: String): Flow<List<City>> = cityDao.getAlphabetizedWords(acs)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }
}