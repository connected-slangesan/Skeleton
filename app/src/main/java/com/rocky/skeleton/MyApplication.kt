package com.rocky.skeleton

import android.app.Application

class MyApplication : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent.builder().build()
    }


    fun getDaggerComponent() : ApplicationComponent {
        return component
    }
}
