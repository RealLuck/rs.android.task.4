package com.realluck.roomwordsample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.realluck.roomwordsample.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao
{

    @Query("SELECT * FROM city_table ORDER BY " +
            "CASE WHEN :asc = 'city' THEN city END ASC," +
            "CASE WHEN :asc = 'country' THEN country END ASC," +
            "CASE WHEN :asc = 'capacity' THEN capacity END ASC")
    fun getAlphabetizedWords(asc: String): Flow<List<City>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()
}