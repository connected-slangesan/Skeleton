package com.rocky.skeleton.home.viewmodel

import androidx.lifecycle.ViewModel
import com.rocky.skeleton.repository.CharactersRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val charactersRepository: CharactersRepository) : ViewModel() {

    fun observeCharacterData() = charactersRepository.observeCharacterData()

    fun fetchCharacters() = charactersRepository.fetchCharacters()
}