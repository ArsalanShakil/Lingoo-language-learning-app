//ARSALAN SHAKIL
//1910097
package com.example.translate.database


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)     //if the database get the same values twice they will be ignored
    //insert the data in database using function addWord()
    fun addWord(word: Words?)

    @Query("SELECT * FROM word_table ORDER BY id ASC")  //sort the data in ascending order
    //fetch the data from database using function readAllData()
    fun readAllData(): LiveData<List<Words>>

    @Update
    //update the data in database using function updateWord()
    fun updateWord(word: Words)
}