package com.example.uts_anmp_160421059.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Paragraph (
    @ColumnInfo(name = "id_game")
    var id_game: String,
    @ColumnInfo(name = "judul_paragraf")
    var judul_paragraf: String,
    @ColumnInfo(name = "isi_paragraf")
    var isi_paragraf:String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}