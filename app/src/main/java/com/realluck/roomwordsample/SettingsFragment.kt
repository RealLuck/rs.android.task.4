package com.realluck.roomwordsample

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener
{
    var  sharedPreferences:SharedPreferences? = null
    var listPreference: ListPreference? = null

    override fun onCreatePreferences(p0: Bundle?, p1: String?)
    {
        setPreferencesFromResource(R.xml.settings, p1)
        listPreference = findPreference<ListPreference>("sort_by")
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        listPreference?.summary = "sort by " + sharedPreferences?.getString("sort_by", "asc")
}

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?)
    {
        listPreference?.summary = "sort by " + sharedPreferences?.getString("sort_by", "asc")
        Log.d("TAG", "sort changed $listPreference")
    }
}
