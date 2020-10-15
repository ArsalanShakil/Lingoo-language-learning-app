@file:Suppress("DEPRECATION")
//ARSALAN SHAKIL
//1910097

package com.example.translate.classes

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translate.R
import kotlinx.android.synthetic.main.activity_words.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


class WordsActivity : AppCompatActivity() {
    lateinit var pDialog: ProgressDialog    //to show the progress dialog
    private val url = "https://users.metropolia.fi/~arsalans/word.json" //set the url of the json file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        lottieAnimationView_notConnected.visibility = View.GONE //hide the animation
        recycler_view_words.layoutManager = LinearLayoutManager(this)
        recycler_view_words.setHasFixedSize(true)
        internetNotConnected() //call internetNotConnected function to check internet connection

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Learn new words daily"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskHandler : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            //show a progress dialog when the activity is opened that says please wait
            pDialog = ProgressDialog(this@WordsActivity)
            pDialog.setMessage("please wait")
            pDialog.setCancelable(false)
            pDialog.show()
        }

        override fun doInBackground(vararg url: String?): String {

            val res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection //connect to the url given as an http link


            try {
                connection.connect() //try connecting
                res = connection.inputStream.use { it.reader().use { reader -> reader.readText() } } //read the data from the file
            } finally {
                connection.disconnect() //disconnect from the file if can not connect
            }
            return res
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (pDialog.isShowing) { //if the progress dialog is showing
                pDialog.cancel()    //close the progress dialog if its showing
                jsonResult(result)  //show result from the json file
            }

        }

        //get the data from the json file
        private fun jsonResult(jsonString: String?) {
            val jsonArray = JSONArray(jsonString)
            val list = ArrayList<Word>()
            var i = 0
            while (i < jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                list.add(
                    Word(
                        //get the words from the json file
                        jsonObject.getString("word"),
                        //get the meanings from the json file
                        jsonObject.getString("meaning")
                    )
                )
                i++
            }
            //set the adapter to recycler view adapter to WordListAdapter
            val adapter = WordListAdapter(this@WordsActivity, list)
            recycler_view_words.adapter = adapter
        }
    }
    //for the back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //if the internet is not connected
    private fun isOnline(): Boolean {
        val conMgr =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            lottieAnimationView_notConnected.visibility = View.VISIBLE //show no wifi animation
            Toast.makeText(
                this,
                "No Internet connection! Please connect to internet",
                Toast.LENGTH_LONG
            ).show() //show a toast that say No internet
            return false
        }
        return true
    }

    //check the internet connection
    private fun internetNotConnected() {
        if (!isOnline()) {
            //if internet is not connected, then cancel the AsyncTaskHandler class
            AsyncTaskHandler().cancel(true)
        } else {
            //if internet is connected then run the AsyncTaskHandler class with the given url
            AsyncTaskHandler().execute(url)
        }
    }
}