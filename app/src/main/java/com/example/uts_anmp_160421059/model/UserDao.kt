package com.example.uts_anmp_160421059.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    fun selectAll(): List<User>

    @Query("SELECT * FROM user WHERE id= :id")
    fun selectUser(id:Int):User

    @Query("SELECT * FROM user where username= :username and password = :password")
    fun login(username: String, password:String): User

    @Update //boleh pake Query manual mau delete,insert,ataupun update
    fun updateUser(user: User)

    @Query("Update user set first_name=:first_name, last_name=:last_name, password=:password where id=:id")
    fun updateProfileUser(first_name: String, last_name:String, password: String,id:Int)

}