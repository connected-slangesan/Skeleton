package com.rocky.skeleton.repository

import com.rocky.skeleton.home.api.CharacterApi
import com.rocky.skeleton.home.model.CharacterData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CharactersRepository @Inject constructor(val api: CharacterApi) {



    //This will initiate the retrofit call and the data is computed in viewmodel
    fun fetchCharacters(): Single<CharacterData>? {
        return api.getCharacters().subscribeOn(Schedulers.io())
    }
}