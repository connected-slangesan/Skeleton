package com.rocky.skeleton.home.api

import com.rocky.skeleton.home.model.CharacterData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {

    @GET("manga/1/characters")
    fun getCharacters() : Single<CharacterData>
}