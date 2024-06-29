package com.example.uts_anmp_160421059.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParagraphDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg paragraph: Paragraph)

    @Query("SELECT * FROM paragraph")
    fun selectAll(): List<Paragraph>

    @Delete
    fun delete(paragraph: Paragraph)

    @Query("SELECT * FROM paragraph where id_game= :id")
    fun selectParagrafDetail(id: Int):List<Paragraph>
}