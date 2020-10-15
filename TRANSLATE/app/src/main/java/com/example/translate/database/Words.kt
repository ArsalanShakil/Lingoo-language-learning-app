//ARSALAN SHAKIL
//1910097

package com.example.translate.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")   //set database name to word_table

//database will contain id,word,correct answer and total number of quizzes taken
data class Words(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val correctAns: Int,
    val total: Int
)