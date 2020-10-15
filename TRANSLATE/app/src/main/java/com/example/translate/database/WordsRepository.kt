//ARSALAN SHAKIL
//1910097
package com.example.translate.database


import androidx.lifecycle.LiveData

class WordsRepository(private val wordDao: WordsDao) {

    val readAllData: LiveData<List<Words>> = wordDao.readAllData()  //read all data

    fun addWord(word: Words) {  //add words in database
        wordDao.addWord(word)
    }

    fun updateWord(word: Words) { //update data in database
        wordDao.updateWord(word)
    }

}