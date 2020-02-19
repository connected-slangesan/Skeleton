package com.rocky.skeleton.home.model

data class CharacterResponse(val isSuccess: Boolean) {
    var data: List<Character>? = null
    var error: String? = null
}