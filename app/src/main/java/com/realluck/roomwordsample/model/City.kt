package com.realluck.roomwordsample.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(@PrimaryKey var city: String = "", var country: String = "", var capacity : Int = 0)

