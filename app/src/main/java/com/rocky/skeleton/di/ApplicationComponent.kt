package com.rocky.skeleton

import com.rocky.skeleton.di.ApplicationModule
import com.rocky.skeleton.di.NetworkModule
import com.rocky.skeleton.di.ViewModelModule
import com.rocky.skeleton.home.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity: HomeActivity)
}