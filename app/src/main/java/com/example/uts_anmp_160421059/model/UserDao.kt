package com.example.uts_anmp_160421059.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    fun selectAll(): List<User>

    @Query("SELECT * FROM user WHERE id= :id")
    fun selectGame(id:Int): User

}