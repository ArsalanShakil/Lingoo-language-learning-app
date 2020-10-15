//ARSALAN SHAKIL
//1910097
package com.example.translate.classes



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.translate.R
import kotlinx.android.synthetic.main.words_list.view.*

class WordListAdapter(val context: Context, private val wordList: ArrayList<Word>) :
    RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        //set the view holder to words_list
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.words_list, parent, false)
        return WordListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currentItem = wordList[position]    //set the currentItem with the data in wordList at that position
        holder.wordTextView.text = currentItem.word     //set the text to word from the wordlist
        holder.meaningTextView.text = currentItem.meaning   //set the text to meaning from the wordList
    }

    override fun getItemCount() = wordList.size //return the size of the wordList

    class WordListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordTextView: TextView = itemView.word_textView //set the wordTextView with the texview with id word_textView
        val meaningTextView: TextView = itemView.meaning_textView   //set the meaningTextView with the texview with id word_textView
    }
}
