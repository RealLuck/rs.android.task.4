package com.realluck.roomwordsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.realluck.roomwordsample.model.City
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(City::class), version = 1, exportSchema = false)
public abstract class CityRoomDatabase: RoomDatabase()
{
    abstract fun cityDao(): CityDao

    private class CityDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            }
        }


    companion object {
        @Volatile
        private var INSTANCE: CityRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CityRoomDatabase
        {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityRoomDatabase::class.java,
                    "word_database"
                ).addCallback(CityDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}