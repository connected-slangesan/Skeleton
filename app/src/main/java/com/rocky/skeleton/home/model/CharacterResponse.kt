package com.rocky.skeleton.home.model

data class CharacterResponse(val isSuccess: Boolean) {
    var data: CharacterData? = null
    var error: String? = null
}