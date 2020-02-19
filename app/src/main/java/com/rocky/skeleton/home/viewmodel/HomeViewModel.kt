package com.rocky.skeleton.home.viewmodel

import androidx.lifecycle.ViewModel
import com.rocky.skeleton.home.model.Character
import com.rocky.skeleton.home.model.CharacterResponse
import com.rocky.skeleton.repository.CharactersRepository
import com.rocky.skeleton.room.CharacterEntity
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class HomeViewModel @Inject constructor(val charactersRepository: CharactersRepository) : ViewModel() {

    private var charactersData: BehaviorSubject<CharacterResponse> = BehaviorSubject.create()
    private var loading: BehaviorSubject<Boolean> = BehaviorSubject.create()


    //Return a Observable back to Activity to monitor the change in data
    fun observeCharacterData() : Observable<CharacterResponse> {
        return charactersData
    }

    //Return a Observable back to Activity to monitor the Loading
    fun observeLoading() : Observable<Boolean> {
        return loading
    }

    fun fetchCharacters() {
        loadingListener(true)

        charactersRepository.fetchCharacters()?.subscribe(
            { data ->
                val response = CharacterResponse(true)
                response.data = data.characters
                charactersData.onNext(response)
            },
            { error ->
                val response = CharacterResponse(false)
                response.error = error.message
                charactersData.onNext(response)
            }
        )
    }

    fun fetchCharactersFromDB() {
        charactersRepository.fetchCharactersFromDb()
    }

    fun getStoredCharactersFromDB() = charactersRepository.getStoredCharactersFromDB()

    fun saveForOfflineStorage(chars: List<Character>) = charactersRepository.saveCharactersToDB(chars)

    fun loadingListener(shouldShow: Boolean) {
        loading.onNext(shouldShow)
    }

    fun convertEntityToCharacter(items: List<CharacterEntity>): List<Character> {

        val characters: MutableList<Character> = ArrayList()

        for (i in items) {
            characters.add(Character(image_url = i.image_url, mal_id = i.mal_id, name = i.name, role = i.role, url = i.url))
        }

        return characters
    }
}