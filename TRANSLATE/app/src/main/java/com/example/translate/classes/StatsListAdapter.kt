//ARSALAN SHAKIL
//1910097

package com.example.translate.classes



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translate.R
import com.example.translate.database.Words
import kotlinx.android.synthetic.main.stats_list.view.*

class StatsListAdapter : RecyclerView.Adapter<StatsListAdapter.MyViewHolder>() {

    private var itemList = emptyList<Words>() //create am empty list of type Word(class)


    override fun onCreateViewHolder(
        parent: ViewGroup,  //set the view holder to stats_list
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.stats_list, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]    //set the currentItem with the data in itemList at that position
        holder.itemView.word_stats_textView.text = currentItem.word //set the text to word in the itemlist
        holder.itemView.totalScore_stats_textView.text = currentItem.total.toString() //set the text to total from the itemlist
        holder.itemView.rightAns_stats_textView.text = currentItem.correctAns.toString()    //set the text to total from the itemlist
    }

    //get the size of the itemList
    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(word: List<Words>) {
        this.itemList = word //set list of Words to itemList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}