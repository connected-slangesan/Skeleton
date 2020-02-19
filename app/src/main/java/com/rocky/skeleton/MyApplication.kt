package com.rocky.skeleton

import android.app.Application
import com.rocky.skeleton.di.ApplicationModule

class MyApplication : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }


    fun getDaggerComponent() : ApplicationComponent {
        return component
    }
}
