package com.example.uts_anmp_160421059.model

import android.icu.text.CaseMap.Title

data class Game (
    var id:Int?,
    var judul:String?,
    var deskripsi:String?,
    var url:String?,
    var authors:String?,
    var paragraphs:List<Paragraph>?
)
data class Paragraph(
    var judul_paragraf: String?,
    var isi_paragraf:String?,
)