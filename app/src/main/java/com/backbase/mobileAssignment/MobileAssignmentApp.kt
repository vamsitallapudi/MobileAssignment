package com.backbase.mobileAssignment

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MobileAssignmentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}