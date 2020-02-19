package com.rocky.skeleton.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocky.skeleton.utils.CHARACTER_DB

@Database(entities = arrayOf(CharacterEntity::class), version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao() : CharacterDao

    companion object {
        private var INSTANCE : CharacterDatabase? = null

        fun getInstance(context: Context) : CharacterDatabase {
            return  Room.databaseBuilder(context, CharacterDatabase::class.java, CHARACTER_DB).build()
        }
    }
}