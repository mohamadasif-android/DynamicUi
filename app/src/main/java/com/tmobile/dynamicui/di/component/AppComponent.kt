package com.tmobile.dynamicui.di.component

import com.tmobile.TMobileApp
import com.tmobile.dynamicui.di.module.ActivityModule
import com.tmobile.dynamicui.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent : AndroidInjector<TMobileApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<TMobileApp>

}