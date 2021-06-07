package com.backbase.mobileAssignment.di
import com.backbase.mobileAssignment.ui.home.HomeActivity

/**
 * Injector for HomeActivity.
 */
fun inject(activity: HomeActivity) {
    DaggerAppComponent.factory()
        .create(activity.applicationContext, activity)
        .inject(activity)
}
