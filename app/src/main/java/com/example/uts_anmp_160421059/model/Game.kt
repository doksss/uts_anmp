package com.example.uts_anmp_160421059.model

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game (
    @ColumnInfo(name = "judul")
    var judul:String,
    @ColumnInfo(name = "deskripsi")
    var deskripsi:String,
    @ColumnInfo(name = "category")
    var category:String,
    @ColumnInfo(name = "pengarang")
    var pengarang:String,
    @ColumnInfo(name = "url")
    var url:String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}