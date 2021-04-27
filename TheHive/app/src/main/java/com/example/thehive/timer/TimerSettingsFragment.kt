package com.example.thehive.timer

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.thehive.R

class TimerSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefernces)
    }
}