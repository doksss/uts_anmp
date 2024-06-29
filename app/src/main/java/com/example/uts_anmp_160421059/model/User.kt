package com.example.uts_anmp_160421059.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class User (
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "first_name")
    var first_name:String,
    @ColumnInfo(name = "last_name")
    var last_name:String,
    @ColumnInfo(name = "email")
    var email:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name="url")
    var url:String,
    @ColumnInfo(name="phone")
    var phone:Int

){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}