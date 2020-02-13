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

class CharactersRepository @Inject constructor(val api: CharacterApi) {

    private var charactersData: BehaviorSubject<CharacterData> = BehaviorSubject.create()

    //Return a Observable back to Activity to monitor the change in data
    fun observeCharacterData() : Observable<CharacterData> {
        return charactersData.subscribeOn(Schedulers.io())
    }

    //This call will notify the new set of data to the observer that was attached
    fun fetchCharacters() {
        api.getCharacters().enqueue(object : Callback<CharacterData> {
            override fun onResponse(call: Call<CharacterData>, response: Response<CharacterData>) {
                if (response.code() == 200) {
                    charactersData.onNext(response.body())
                }
            }
            override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                TODO()
            }
        })
    }
}