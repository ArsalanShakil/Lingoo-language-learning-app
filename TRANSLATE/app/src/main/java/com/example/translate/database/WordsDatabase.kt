//ARSALAN SHAKIL
//1910097
package com.example.translate.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Words::class], version = 1, exportSchema = false
)
//word class extends RoomDatabase
abstract class WordsDatabase : RoomDatabase() {

    abstract fun wordDao(): WordsDao    //will retrun data access object

    companion object {
        @Volatile   //visibile to other threads immediately
        private var INSTANCE: WordsDatabase? = null //create word database singleton class


        fun getDatabase(context: Context): WordsDatabase {
            val tempInstance = INSTANCE
            //if intsance does not exist
            if (tempInstance != null) {
                return tempInstance //retrun intance
            }
            synchronized(this) {    //protected from concurrent execution via multiple threads
                val instance = Room.databaseBuilder(    //creating an intance of Room Database
                    context.applicationContext,
                    WordsDatabase::class.java,
                    "word_database"
                ).build()   //call the database builder to build the database
                INSTANCE = instance
                return instance
            }
        }

    }
}