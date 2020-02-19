package com.rocky.skeleton.di

import android.app.Application
import com.rocky.skeleton.MyApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule constructor(val application: Application) {

    @Provides
    fun bindApplicationContext() = application.applicationContext
}