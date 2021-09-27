package com.example.marvelapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.marvelapplication.model.Character

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "favoriteCharacter"
        private val CHARACTER_TABLE = "CHARACTER_TABLE"
        private val COLUMN_CHARACTER_NAME = "COLUMN_CHARACTER_NAME"
        private val COLUMN_CHARACTER_IMAGE = "COLUMN_CHARACTER_IMAGE"
        private val COLUMN_PERSON_ID = "COLUMN_PERSON_ID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + CHARACTER_TABLE + " (" + COLUMN_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_CHARACTER_NAME + " TEXT, " + COLUMN_CHARACTER_IMAGE + " TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addCharacter(character: Character): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_CHARACTER_NAME, character.name)
        contentValues.put(COLUMN_CHARACTER_IMAGE, character.image)
        val success = db.insert(CHARACTER_TABLE, null, contentValues)
        db.close()
        return success != 1L
    }

    fun deleteFavorite(name: String): Int {
        val db = this.writableDatabase
        val result = db.delete(CHARACTER_TABLE, COLUMN_CHARACTER_NAME + " = ?", arrayOf(name))
        return result
    }

    fun getEveryone(): List<Character> {
        var returnList = mutableListOf<Character>()
        val queryString = "SELECT * FROM " + CHARACTER_TABLE
        val db = this.writableDatabase
        val cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(1)
                val image = cursor.getString(2)
                val character = Character(name, image)
                //Log.e("Name: ", "" + character.name)
                returnList.add(character)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return returnList
    }
}