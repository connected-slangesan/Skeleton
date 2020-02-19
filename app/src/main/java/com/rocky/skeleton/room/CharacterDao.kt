package com.rocky.skeleton.room

import androidx.room.*
import com.rocky.skeleton.home.model.Character
import retrofit2.http.DELETE

@Dao
abstract class CharacterDao {

    @Query("SELECT * FROM characters")
    abstract fun getAllCharacters() : List<CharacterEntity>

    fun getAllCharactersFromDB() : List<Character> {

        val characters: MutableList<Character> = ArrayList()

        val list = getAllCharacters()

        for (i in list) {
            characters.add(Character(image_url = i.image_url, mal_id = i.mal_id, name = i.name, role = i.role, url = i.url))
        }

        return characters
    }


    fun insertCharacter(character: Character) {
        insert(CharacterEntity(image_url = character.image_url, mal_id = character.mal_id,
        name = character.name,
        role = character.role,
        url = character.url))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(character: CharacterEntity)

    @Delete
    abstract fun deleteCharacter(characterEntity: CharacterEntity)
}