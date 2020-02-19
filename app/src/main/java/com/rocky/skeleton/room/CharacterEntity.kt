package com.rocky.skeleton.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
class CharacterEntity(val image_url: String,
                      val mal_id: Int,
                      val name: String,
                      val role: String,
                      val url: String)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}