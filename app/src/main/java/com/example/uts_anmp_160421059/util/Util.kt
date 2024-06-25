package com.example.uts_anmp_160421059.util

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.uts_anmp_160421059.model.GameDatabase

val DB_NAME = "gamedb"


fun buildDb(context: Context): GameDatabase {
    val db = GameDatabase.buildDatabase(context)
    return db
}