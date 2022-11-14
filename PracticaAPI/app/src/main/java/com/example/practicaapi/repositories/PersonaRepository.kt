package com.example.practicaapi.repositories

import com.example.practicaapi.api.JSONPlaceHolderAPI
import com.example.practicaapi.models.Personas
import com.example.practicaapi.models.Post
import retrofit2.Call

object PersonaRepository {

    fun getPersonas(listener: PersonaListListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI::class.java)
        jsonPlaceHolderAPI.getPersonas().enqueue(object : retrofit2.Callback<List<Personas>> {
            override fun onResponse(
                call: Call<List<Personas>>,
                response: retrofit2.Response<List<Personas>>
            ) {
                if (response.isSuccessful) {
                    listener.onPersonaListSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<Personas>>, t: Throwable) {
                listener.onPersonaListFailure(t)
            }
        })
    }

    fun createPersona(persona: Personas, listener: CreatePersonaListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI::class.java)
        jsonPlaceHolderAPI.createPersona(persona).enqueue(object : retrofit2.Callback<Personas> {
            override fun onResponse(
                call: Call<Personas>,
                response: retrofit2.Response<Personas>
            ) {
                if (response.isSuccessful) {
                    listener.onCreatePersonaSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                listener.onCreatePersonaFailure(t)
            }
        })
    }

    fun editPersona(id: Int, persona: Personas, listener: EditPersonaListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI::class.java)
        jsonPlaceHolderAPI.editPersona(id, persona).enqueue(object : retrofit2.Callback<Personas> {
            override fun onResponse(
                call: Call<Personas>,
                response: retrofit2.Response<Personas>
            ) {
                if (response.isSuccessful) {
                    listener.onEditPersonaSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                listener.onEditPersonaFailure(t)
            }
        })
    }

    fun deletePersona(id: Int, listener: DeletePersonaListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI::class.java)
        jsonPlaceHolderAPI.deletePersona(id).enqueue(object : retrofit2.Callback<Personas> {
            override fun onResponse(
                call: Call<Personas>,
                response: retrofit2.Response<Personas>
            ) {
                if (response.isSuccessful) {
                    listener.onDeletePersonaSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                listener.onDeletePersonaFailure(t)
            }
        })
    }

    interface PostByIdListener {
        fun onPostByIdSuccess(post: Post?)
        fun onPostByIdFailure(t: Throwable)
    }

    interface PersonaListListener {
        fun onPersonaListSuccess(personas: List<Personas>?)
        fun onPersonaListFailure(t: Throwable)
    }

    interface CreatePersonaListener {
        fun onCreatePersonaSuccess(personas: Personas?)
        fun onCreatePersonaFailure(t: Throwable)
    }

    interface EditPersonaListener {
        fun onEditPersonaSuccess(personas: Personas?)
        fun onEditPersonaFailure(t: Throwable)
    }

    interface DeletePersonaListener {
        fun onDeletePersonaSuccess(personas: Personas?)
        fun onDeletePersonaFailure(t: Throwable)
    }
}