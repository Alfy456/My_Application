package com.example.myapplication.retrofit

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.myapplication.databinding.ActivityRetrofitBinding
import com.example.myapplication.retrofit.data.Albums
import com.example.myapplication.retrofit.data.AlbumsItem
import retrofit2.Response

class RetrofitActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRetrofitBinding
    private lateinit var retrofitService : AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitService  = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        //getQueryParameterResponse()
       // getPathParameterResponse()

        uploadAlbum()

    }
    private fun getQueryParameterResponse(){

        val responseLiveData : LiveData<Response<Albums>> = liveData {
            val response = retrofitService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this,Observer{
            val albumsList = it.body()?.listIterator()
            if (albumsList != null){
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    val result = " "+ "Album tile : ${albumsItem.title}"+ "\n"+
                            " "+ "Album Id : ${albumsItem.id}"+ "\n"+
                            " "+ "User Id : ${albumsItem.userId}"+"\n\n\n"

                    binding.txtItems.append(result)


                }

            }
        })
    }

    private fun getPathParameterResponse(){
        //get the response from path Parameters
        val resultLiveData: LiveData<Response<AlbumsItem>> = liveData {
            val result = retrofitService.getAlbum(1)
            emit(result)
        }

        resultLiveData.observe(this,Observer{
            val item = it.body()?.title
            Toast.makeText(this,item,Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum(){
        val album = AlbumsItem(0,"my title",3)
        val postResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, Observer {
            val receivedItem = it.body()
            val result = " "+ "Album tile : ${receivedItem?.title}"+ "\n"+
                    " "+ "Album Id : ${receivedItem?.id}"+ "\n"+
                    " "+ "User Id : ${receivedItem?.userId}"+"\n\n\n"

            binding.txtItems.text = result
        })
    }
}