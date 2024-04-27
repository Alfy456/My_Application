package com.example.myapplication.RecyclerViewDemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.R

class RecyclerViewAdapter(
    private val fruitsList: List<Fruit>,
    private val clickListener: (Fruit)->Unit
) : RecyclerView.Adapter<myViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return myViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val item = fruitsList[position]
        holder.bind(item,clickListener)
    }
}
class myViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit){
        val textItem = view.findViewById<TextView>(R.id.txt_item)
        textItem.text = fruit.name

        view.setOnClickListener {
            clickListener(fruit)
        }
    }



}