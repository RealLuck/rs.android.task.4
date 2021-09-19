package com.realluck.roomwordsample

import android.app.Application
import com.realluck.roomwordsample.db.CityRepository
import com.realluck.roomwordsample.db.CityRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CityApplication : Application()
{
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CityRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CityRepository(database.cityDao()) }
}