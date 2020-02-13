package com.rocky.skeleton.home.model

data class CharacterData(
    var characters: List<Character>,
    var request_cache_expiry: Int,
    var request_cached: Boolean,
    var request_hash: String
)