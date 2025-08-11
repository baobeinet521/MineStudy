package com.zd.study.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbOpenHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_BOOK_TABLE = ("CREATE TABLE IF NOT EXISTS " +
            BOOK_TABLE_NAME +"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT)")
    private val CREATE_USER_TABLE = ("CREATE TABLE IF NOT EXISTS " +
            USER_TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT," + "sex INT)")
    companion object {
        private const val DATABASE_NAME = "book_provider.db"
        private const val BOOK_TABLE_NAME = "book"
        private const val USER_TABLE_NAME = "user"
        private const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOK_TABLE)
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}