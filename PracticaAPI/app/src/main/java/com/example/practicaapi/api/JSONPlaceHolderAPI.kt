package com.example.practicaapi.api

import com.example.practicaapi.models.Personas
import com.example.practicaapi.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONPlaceHolderAPI {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("personas")
    fun getPersonas(): Call<List<Personas>>

    @POST("personas")
    fun createPersona(@Body personas: Personas): Call<Personas>

    @POST("personas/{id}")
    fun editPersona(@Path("id") id: Int, @Body personas: Personas): Call<Personas>

    @POST("personas/{id}/delete")
    fun deletePersona(@Path("id") id: Int): Call<Personas>
}
