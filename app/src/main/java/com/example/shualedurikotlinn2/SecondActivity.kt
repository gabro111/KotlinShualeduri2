package com.example.shualedurikotlinn2

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shualedurikotlinn2.adapter.CommentRecyclerViewAdapter
import com.example.shualedurikotlinn2.api.CommentApi
import com.example.shualedurikotlinn2.api.RetrofitClient
import com.example.shualedurikotlinn2.room.App
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    lateinit var titleText: TextView
    private lateinit var commentRecyclerViewAdapter: CommentRecyclerViewAdapter
    lateinit var bodyText: TextView
    private lateinit var commentRecyclerView: RecyclerView;
    private lateinit var textView:TextView
     private var comments:MutableList<CommentApi> = mutableListOf()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



            titleText = findViewById(R.id.secondTitle)
            bodyText = findViewById(R.id.secondBody)
            textView= findViewById(R.id.textView4)
            commentRecyclerView = findViewById(R.id.commentRecyclerView)
            val postId: Long? = intent.extras?.getLong("postId")
            getComments(postId)
            val post = App.instance.db.getPostDao().getPost(postId)




            titleText.text = post.title
            bodyText.text = post.body

        commentRecyclerViewAdapter = CommentRecyclerViewAdapter(comments)
        commentRecyclerView.layoutManager=LinearLayoutManager(titleText.context)
        commentRecyclerView.adapter = commentRecyclerViewAdapter

        getComments(postId)

        textView.text = commentRecyclerViewAdapter.itemCount.toString()







    }

private fun getComments(postId:Long?){

    RetrofitClient.reqResApi.getPostComment(postId)
        .enqueue(object : Callback<List<CommentApi>> {

            override fun onResponse(
                call: Call<List<CommentApi>>,
                response: Response<List<CommentApi>>
            ) {
                if(response.isSuccessful) {
                    comments = response.body() as MutableList<CommentApi>
                    commentRecyclerViewAdapter.setUpdatedData(comments)
                    textView.text = commentRecyclerViewAdapter.itemCount.toString()
                }
            }

            override fun onFailure(call: Call<List<CommentApi>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
            }

        })

}
}