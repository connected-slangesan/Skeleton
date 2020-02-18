package com.rocky.skeleton.home.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rocky.skeleton.MyApplication
import com.rocky.skeleton.R
import com.rocky.skeleton.databinding.ActivityMainBinding
import com.rocky.skeleton.home.CharacterAdapter
import com.rocky.skeleton.home.viewmodel.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        observers()
    }

    //Observe for data transmission or events here
    private fun observers() {
        homeViewModel.observeCharacterData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                homeViewModel.loadingListener(false)

                if (response.isSuccess) {
                    list.adapter =
                        response.data?.characters?.let { CharacterAdapter(it, this, homeViewModel) }
                } else {
                    Toast.makeText(this, response.error, Toast.LENGTH_SHORT).show()
                }

            })

        homeViewModel.observeLoading()
            .subscribe({ show->
                if (show) {
                    progress.visibility = View.VISIBLE
                } else {
                    progress.visibility = View.GONE
                }
            })
    }

    private fun init() {
        //Init Dagger
        (application as MyApplication).getDaggerComponent().inject(this)

        //Init ViewModel
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        //Bind Model to XML
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = homeViewModel

        //Init RecyclerView
        list.setLayoutManager(
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )
        )
    }
}
