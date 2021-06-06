package com.backbase.mobileAssignment.di.modules.home
import com.backbase.mobileAssignment.di.DaggerAppComponent
import com.backbase.mobileAssignment.ui.home.HomeActivity

/**
 * Injector for HomeActivity.
 */
fun inject(activity: HomeActivity) {
    DaggerAppComponent.factory()
        .create(activity.applicationContext, activity)
        .inject(activity)
}
