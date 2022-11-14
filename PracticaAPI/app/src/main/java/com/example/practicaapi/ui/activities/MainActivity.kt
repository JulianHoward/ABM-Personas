package com.example.practicaapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapi.R
import com.example.practicaapi.models.Post
import com.example.practicaapi.repositories.PersonaRepository
import com.example.practicaapi.ui.adapters.PersonaAdapter

class MainActivity : AppCompatActivity(), PersonaRepository.PostListListener,
    PersonaRepository.PostByIdListener {
    private lateinit var btnCallApi: Button
    private lateinit var lstPosts: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCallApi = findViewById(R.id.btnCallApi)
        lstPosts = findViewById(R.id.lstPosts)
        setupEventListeners()
        callPostListApi()
    }

    private fun setupEventListeners() {
        btnCallApi.setOnClickListener {
            callPostApi()
        }
    }

    //#region PostList API
    private fun callPostListApi() {
        PersonaRepository.getPosts(this)
    }

    override fun onPostListSuccess(posts: List<Post>?) {
        val adapter = PersonaAdapter(posts as ArrayList<Post>)
        lstPosts.layoutManager =
            LinearLayoutManager(this@MainActivity)
        lstPosts.adapter = adapter
    }

    override fun onPostListFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    //#endregion
    //#region PostById API
    private fun callPostApi() {
        PersonaRepository.getPost(1, this)
    }

    override fun onPostByIdSuccess(post: Post?) {
        Toast.makeText(this@MainActivity, post?.title, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPostByIdFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
    //#endregion
}