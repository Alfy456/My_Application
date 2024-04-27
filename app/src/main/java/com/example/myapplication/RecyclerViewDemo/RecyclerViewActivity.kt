package com.example.myapplication.RecyclerViewDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private val fruitsList = listOf<Fruit>(
        Fruit("Apple"),
        Fruit("Orange"),
        Fruit("Grape"),
        Fruit("Pear"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerViewAdapter(fruitsList
        ) { selectedItem: Fruit -> itemClicked(selectedItem) }


    }

    fun itemClicked(fruit: Fruit){
        Toast.makeText(this@RecyclerViewActivity, fruit.name, Toast.LENGTH_SHORT).show()
    }
}