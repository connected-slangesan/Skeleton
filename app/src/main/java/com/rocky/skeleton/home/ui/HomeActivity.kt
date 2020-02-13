package com.rocky.skeleton.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rocky.skeleton.MyApplication
import com.rocky.skeleton.R
import com.rocky.skeleton.home.viewmodel.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        observers()

        homeViewModel.fetchCharacters()
    }

    private fun observers() {
        homeViewModel.observeCharacterData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                // consume
                Toast.makeText(this, "" + response, Toast.LENGTH_SHORT).show()
            }, { error ->
                // error handling
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show()
            })
    }

    private fun init() {
        //Init Dagger
        (application as MyApplication).getDaggerComponent().inject(this)

        //Init ViewModel
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }
}
