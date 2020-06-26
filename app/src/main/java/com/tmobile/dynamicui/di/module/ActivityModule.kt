package com.tmobile.dynamicui.di.module

import com.tmobile.dynamicui.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

}