package com.backbase.mobileAssignment.di.modules.home
import android.content.Context
import com.backbase.mobileAssignment.di.DaggerAppComponent
import com.backbase.mobileAssignment.ui.home.HomeActivity

/**
 * Injector for HomeActivity.
 */
fun inject(activity: HomeActivity, applicationContext: Context) {
    DaggerAppComponent.factory()
        .create(applicationContext, activity)
        .inject(activity)
}
