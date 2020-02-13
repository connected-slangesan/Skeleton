package com.rocky.skeleton.repository

import com.rocky.skeleton.home.api.CharacterApi
import com.rocky.skeleton.home.model.CharacterData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CharactersRepository @Inject constructor(val retrofit: Retrofit) {

    private var charactersData: BehaviorSubject<CharacterData> = BehaviorSubject.create()

    fun observeCharacterData() : Observable<CharacterData> {
        return charactersData.subscribeOn(Schedulers.io())
    }

    fun fetchCharacters() {
        retrofit.create(CharacterApi::class.java).getCharacters().enqueue(object : Callback<CharacterData> {
            override fun onResponse(call: Call<CharacterData>, response: Response<CharacterData>) {
                if (response.code() == 200) {
                    charactersData.onNext(response.body())
                }
            }
            override fun onFailure(call: Call<CharacterData>, t: Throwable) {

            }
        })
    }
}