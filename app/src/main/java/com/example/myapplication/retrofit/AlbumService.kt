package com.example.myapplication.retrofit

import com.example.myapplication.retrofit.data.Albums
import com.example.myapplication.retrofit.data.AlbumsItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int) : Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") albumId:Int): Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body albumsItem: AlbumsItem): Response<AlbumsItem>
}