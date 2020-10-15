//ARSALAN SHAKIL
//1910097

package com.example.translate.classes



import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translate.R
import com.example.translate.database.StatsActivityViewModel
import com.example.translate.database.Words
import kotlinx.android.synthetic.main.activity_stats.*


class StatsActivity : AppCompatActivity() {

    private lateinit var mStatsViewModel: StatsActivityViewModel    //to set the data in database
    private lateinit var nStatsViewModel: StatsActivityViewModel    //add data to database
    private lateinit var oStatsViewModel: StatsActivityViewModel    //update data in database
    private var total: Int = 0
    private var idsList: ArrayList<*>? = null
    private lateinit var preference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)





        //get the data of total number of times result activity is opened from Result activity
        total = intent.getIntExtra("total", 0)
        onSave()
        //get the data of list of ids of correct answers from Result activity
        idsList = intent.getSerializableExtra("ids") as ArrayList<*>?

        //put StatsViewModel into the mStatsViewModel variable
        mStatsViewModel = ViewModelProvider(this).get(StatsActivityViewModel::class.java)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Check Progress"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        //set the RecyclerView to linear layout
        val adapter = StatsListAdapter()
        recycler_view_stats.layoutManager = LinearLayoutManager(this)
        recycler_view_stats.adapter = adapter //set the recycler view adapter to StatsListAdapter

        ////put StatsViewModel into the nStatsViewModel variable
        nStatsViewModel = ViewModelProvider(this).get(StatsActivityViewModel::class.java)


        //read all the data from the database
        nStatsViewModel.readAllData.observe(this, { word -> adapter.setData(word) })

        //put StatsViewModel into the oStatsViewModel variable
        oStatsViewModel = ViewModelProvider(this).get(StatsActivityViewModel::class.java)

        //create the Database only once when the shared preferences is true
        preference = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = preference.getBoolean("firstStart", true)
        if (firstStart) {
            //initialize database
            insertDataToDatabase()
        } else {
            //update database if shared preferences is false
            updateItem()
        }

    }
    //Initilize database and set words into it
    private fun insertDataToDatabase() {
        var word = Words(1, "koira", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(2, "kirja", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(3, "pallo", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(4, "ruokaa", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(5, "kengät", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(6, "juoda", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(7, "nukkua", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(8, "elokuva", 0, 0)
        mStatsViewModel.addWord(word)
        word = Words(9, "pelata", 0, 0)
        mStatsViewModel.addWord(word)

        //once the data base is initialized edit the shared preferences to false so it does not get initialized again
        val editor = preference.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
        //make a toast data added
        Toast.makeText(this, "Data added!", Toast.LENGTH_LONG).show()
    }
    //update the data of the database
    private fun updateItem() {
        //edit data in shared preferences and increase it ,so it remembers the total number of quizzes taken
        val pref = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        var totalQues = pref.getInt("total_questions", 0)
        totalQues += total
        val editor = pref.edit()
        editor.putInt("total_questions", totalQues)
        editor.apply()
        //if the list of ids with correct answers is not empty update the data
        if (idsList != null) {
            val correctAns = 0
            //change the data and put the number of total quizzes taken and the correct answers
            var updateWord = Words(1, "koira", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(2, "kirja", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(3, "pallo", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(4, "ruokaa", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(5, "kengät", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(6, "juoda", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(7, "nukkua", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(8, "elokuva", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)
            updateWord = Words(9, "pelata", correctAns, totalQues)
            oStatsViewModel.updateWord(updateWord)

            //loop through the list of ids of correct answers
            for (i in 0 until idsList!!.size) {
                var correctAns = 0
                when {
                    //if the ids list contains the id 1 then increase the correct answer in word database
                    idsList!![i] == 1 -> {
                        correctAns++
                        val updateWord1 = Words(1, "koira", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord1)

                    }
                    //if the ids list contains the id 2 then increase the correct answer in word database
                    idsList!![i] == 2 -> {
                        correctAns++
                        val updateWord2 = Words(2, "kirja", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord2)
                    }
                    //if the ids list contains the id 3 then increase the correct answer in word database
                    idsList!![i] == 3 -> {
                        correctAns++
                        val updateWord3 = Words(3, "pallo", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord3)
                    }
                    //if the ids list contains the id 4 then increase the correct answer in word database
                    idsList!![i] == 4 -> {
                        correctAns++
                        val updateWord4 = Words(4, "ruokaa", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord4)
                    }
                    //if the ids list contains the id 5 then increase the correct answer in word database
                    idsList!![i] == 5 -> {
                        correctAns++
                        val updateWord5 = Words(5, "kengät", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord5)
                    }
                    //if the ids list contains the id 6 then increase the correct answer in word database
                    idsList!![i] == 6 -> {
                        correctAns++
                        val updateWord6 = Words(6, "juoda", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord6)
                    }
                    //if the ids list contains the id 7 then increase the correct answer in word database
                    idsList!![i] == 7 -> {
                        correctAns++
                        val updateWord7 = Words(7, "nukkua", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord7)
                    }
                    //if the ids list contains the id 8 then increase the correct answer in word database
                    idsList!![i] == 8 -> {
                        correctAns++
                        val updateWord8 = Words(8, "elokuva", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord8)
                    }
                    //if the ids list contains the id 9 then increase the correct answer in word database
                    else -> {
                        correctAns++
                        val updateWord9 = Words(9, "pelata", correctAns, totalQues)
                        oStatsViewModel.updateWord(updateWord9)
                    }
                }

            }
        }


    }
    //save data in shared preferences,so it remembers the total number of quizzes taken
    private fun onSave() {
        val pref: SharedPreferences = getSharedPreferences("sharedsprefs", Context.MODE_PRIVATE)
        val editor = pref.edit()
        //if total(number of times Result activity is opened) is not 0 then save it in shared preferences
        if (total > 0) {
            editor.putInt("total_questions", total)
            editor.apply()
        }
    }
    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}