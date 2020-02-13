package com.rocky.skeleton.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rocky.skeleton.MyApplication
import com.rocky.skeleton.R
import com.rocky.skeleton.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        //Init Dagger
        (application as MyApplication).getDaggerComponent().inject(this)

        //Init ViewModel
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }
}
