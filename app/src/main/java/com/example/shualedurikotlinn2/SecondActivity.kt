package com.example.shualedurikotlinn2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shualedurikotlinn2.adapter.CommentRecyclerViewAdapter
import com.example.shualedurikotlinn2.api.ApiMethods
import com.example.shualedurikotlinn2.api.CommentApi
import com.example.shualedurikotlinn2.room.App
import com.example.shualedurikotlinn2.room.Post
import kotlinx.coroutines.runBlocking

class SecondActivity: AppCompatActivity(R.layout.activity_second) {


    lateinit var post: Post
    private val apiMethods = ApiMethods()

    var comments: List<CommentApi>? = null

    lateinit var titleText :TextView
    private lateinit var commentRecyclerViewAdapter:CommentRecyclerViewAdapter
    lateinit var bodyText: TextView
    private lateinit var commentRecyclerView: RecyclerView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleText = findViewById(R.id.secondTitle)
        bodyText = findViewById(R.id.secondBody)
        commentRecyclerView = findViewById(R.id.commentRecyclerView)
        val postId: Long? = intent.extras?.getLong("postId")

        val post = App.instance.db.getPostDao().getPost(postId)

        titleText.text = post.title
        bodyText.text = post.body

        runBlocking {
            comments = apiMethods.getPostComment(postId)
        }

        commentRecyclerViewAdapter = CommentRecyclerViewAdapter(comments)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.adapter = commentRecyclerViewAdapter



    }


}