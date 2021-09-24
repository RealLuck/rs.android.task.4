package com.realluck.roomwordsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewCityActivity : AppCompatActivity(){

    private lateinit var editCityView: EditText
    private lateinit var editCountyView: EditText
    private lateinit var editCapacityView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editCityView = findViewById(R.id.city)
        editCountyView = findViewById(R.id.country)
        editCapacityView = findViewById(R.id.capacity)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
                val city = editCityView.text.toString()
                val county = editCountyView.text.toString()
                val capacity = editCapacityView.text.toString()
            val cityArray = arrayOf(city, county, capacity)
                replyIntent.putExtra(EXTRA_REPLY, cityArray)
                setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}