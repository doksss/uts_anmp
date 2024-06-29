package com.example.uts_anmp_160421059.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.uts_anmp_160421059.model.GameDatabase

val DB_NAME = "gamedb"


fun buildDb(context: Context): GameDatabase {
    val db = Room.databaseBuilder(context,
    GameDatabase::class.java, DB_NAME)
    .addMigrations(MIGRATION_1_2).build()
    return db

}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE user ADD COLUMN phone DEFAULT 0 not null")
    }
}
