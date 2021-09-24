package com.realluck.roomwordsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.realluck.roomwordsample.model.City

class MainActivity : AppCompatActivity()
{
    private val cityViewModel: CityViewModel by viewModels {
        CityViewModelFactory((application as CityApplication).repository)
    }
    private val newCityActivityRequestCode = 1
    private val newSettingsActivityRequestCode = 2


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CityListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val asc = sharedPreferences.getString("sort_by", "asc") ?: "asc"
        cityViewModel.cityListLiveData.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter?.submitList(it) }
            Log.d("TAG", "$asc")
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCityActivity::class.java)
            startActivityForResult(intent, newCityActivityRequestCode)
        }
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val intent = Intent(this@MainActivity,SettingsActivity::class.java)
            startActivityForResult(intent, newSettingsActivityRequestCode)
        }
    }

    override fun onResume()
    {
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCityActivityRequestCode && resultCode == Activity.RESULT_OK)
        {
            data?.getStringArrayExtra(NewCityActivity.EXTRA_REPLY)?.let {
                val city = City(it[0], it[1], it[2].toInt())
                cityViewModel.insert(city)
            }
        } else if (requestCode == newSettingsActivityRequestCode){
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val asc = sharedPreferences.getString("sort_by", "asc") ?: "asc"
            cityViewModel.sort(asc)
        }
        else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

}