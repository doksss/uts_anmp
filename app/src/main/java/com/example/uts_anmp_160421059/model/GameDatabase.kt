package com.example.uts_anmp_160421059.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uts_anmp_160421059.util.DB_NAME

@Database(entities =[Game::class,User::class,Paragraph::class], version = 1)
abstract class GameDatabase:RoomDatabase() {
    abstract fun gameDao():GameDao
    abstract fun userDao():UserDao
    abstract fun paragraphDao():ParagraphDao
    companion object{
        @Volatile private var instance: GameDatabase ?= null
        private val LOCK = Any()
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                DB_NAME).build()
        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}