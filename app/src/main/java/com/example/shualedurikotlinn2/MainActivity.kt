package com.example.shualedurikotlinn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shualedurikotlinn2.adapter.RecycleViewAdapter
import com.example.shualedurikotlinn2.api.ApiMethods
import com.example.shualedurikotlinn2.api.CommentApi
import com.example.shualedurikotlinn2.api.PostApi
import com.example.shualedurikotlinn2.api.RetrofitClient
//import com.example.shualedurikotlinn2.api.ReqResData
import com.example.shualedurikotlinn2.room.App
import com.example.shualedurikotlinn2.room.Post
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apiMethods = ApiMethods()
    private lateinit var recyclerViewAdapter: RecycleViewAdapter
    lateinit var posts: List<Post>
    private lateinit var mainRecyclerView: RecyclerView;

    lateinit var swipeRefresh :SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //   App.instance.db.getPostDao().deleteAllPosts()
        swipeRefresh = findViewById(R.id.swipeRefresh)
        mainRecyclerView = findViewById(R.id.mainRecycle)


        if (App.instance.db.getPostDao().getAllPosts().isEmpty()) {
                //wesivrad ver gaverkvie kotlinis async await-shi wesit minda ro daelodos sanam moxdeba
                // getPosts metodi da mere chaweros
            runBlocking {
                apiMethods.getPosts()

            }
        }

        posts = App.instance.db.getPostDao().getAllPosts()


        recyclerViewAdapter = RecycleViewAdapter(posts)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = recyclerViewAdapter

        swipeRefresh.setOnRefreshListener {
            App.instance.db.getPostDao().deleteAllPosts()
            runBlocking {
                apiMethods.getPosts()

            }
            recyclerViewAdapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}