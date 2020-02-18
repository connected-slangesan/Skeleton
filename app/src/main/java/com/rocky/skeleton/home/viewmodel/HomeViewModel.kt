package com.rocky.skeleton.home.viewmodel

import androidx.lifecycle.ViewModel
import com.rocky.skeleton.home.model.CharacterResponse
import com.rocky.skeleton.repository.CharactersRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
                response.data = data
                charactersData.onNext(response)
            },
            { error ->
                val response = CharacterResponse(false)
                response.error = error.message
                charactersData.onNext(response)
            }
        )
    }

    fun loadingListener(shouldShow: Boolean) {
        loading.onNext(shouldShow)
    }
}