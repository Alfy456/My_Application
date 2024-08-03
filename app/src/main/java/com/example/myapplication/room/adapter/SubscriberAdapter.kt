package com.example.myapplication.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.SubscribersListItemBinding
import com.example.myapplication.generated.callback.OnClickListener
import com.example.myapplication.room.db.Subscriber

class SubscriberAdapter(private val onClickListener: (Subscriber) -> Unit
): RecyclerView.Adapter<MyViewHolder>() {

    private val subscribersList= ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubscribersListItemBinding? =
            DataBindingUtil.inflate(layoutInflater, R.layout.subscribers_list_item,parent,false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position],onClickListener)
    }

    fun setList(subscribers: List<Subscriber>){
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }
}

class MyViewHolder(val binding: SubscribersListItemBinding?): RecyclerView.ViewHolder(binding?.root!!){

    fun bind( subscribers: Subscriber, onClickListener: (Subscriber) -> Unit){
        binding?.tvName?.text = subscribers.name
      //  binding?.tvEmail?.text = subscribers.email
        binding?.tvNum?.text = subscribers.phoneNumber
        binding?.tvAddress?.text = subscribers.address
        binding?.subListView?.setOnClickListener{
            onClickListener(subscribers)
        }
    }
}