package com.rocky.skeleton.repository

import android.content.Context
import com.rocky.skeleton.home.api.CharacterApi
import com.rocky.skeleton.home.model.Character
import com.rocky.skeleton.home.model.CharacterData
import com.rocky.skeleton.home.model.CharacterResponse
import com.rocky.skeleton.room.CharacterDatabase
import com.rocky.skeleton.room.CharacterEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CharactersRepository @Inject constructor(val api: CharacterApi, val context: Context) {

    private var charactersData: BehaviorSubject<List<Character>> = BehaviorSubject.create()

    //This will initiate the retrofit call and the data is computed in viewmodel
    fun fetchCharacters(): Single<CharacterData>? {
        return api.getCharacters().subscribeOn(Schedulers.io())
    }

    fun getStoredCharactersFromDB() = charactersData

    fun fetchCharactersFromDb() {

        Single.fromCallable {
            val items = CharacterDatabase.getInstance(context).getCharacterDao().getAllCharactersFromDB()

            charactersData.onNext(items)
        }
            .subscribeOn(Schedulers.newThread())
            .subscribe()
    }

    fun saveCharactersToDB(chars: List<Character>) {
        Single.fromCallable {
            for (i in chars) {
                CharacterDatabase.getInstance(context).getCharacterDao().insertCharacter(i)
            }
        }
            .subscribeOn(Schedulers.newThread())
            .subscribe()
    }
}