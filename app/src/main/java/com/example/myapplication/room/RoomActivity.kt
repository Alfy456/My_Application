package com.example.myapplication.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityRoomBinding
import com.example.myapplication.room.adapter.SubscriberAdapter
import com.example.myapplication.room.db.Subscriber
import com.example.myapplication.room.db.SubscriberDatabase
import com.example.myapplication.room.db.SubscriberRepository
import com.example.myapplication.room.viewmodel.SubscriberViewModel
import com.example.myapplication.room.viewmodel.SubscriberViewModelFactory
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var adapter: SubscriberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.subscriberViewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()


        viewModel.message.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun initRecyclerView(){
        binding.rvSubscriber.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberAdapter{ selectedItem: Subscriber ->
            listItemSelected(
                selectedItem
            )}
        binding.rvSubscriber.adapter = adapter
        getSubscribers()
    }

    private  fun getSubscribers(){

            viewModel.getSavedSubscribers().observe ( this, Observer {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } )

        }


    private fun listItemSelected(subscriber: Subscriber){
      //  Toast.makeText(this,"Selected name is ${subscriber.name}", Toast.LENGTH_SHORT).show()
        viewModel.initUpdateAndDelete(subscriber)
    }


}