//ARSALAN SHAKIL
//1910097

package com.example.translate.database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StatsActivityViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Words>>
    private val repository: WordsRepository

    init {
        val wordDao = WordsDatabase.getDatabase(application).wordDao()
        repository = WordsRepository(wordDao)
        readAllData = repository.readAllData
    }

    fun addWord(word: Words) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(word)
        }
    }

    fun updateWord(word: Words) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }
}