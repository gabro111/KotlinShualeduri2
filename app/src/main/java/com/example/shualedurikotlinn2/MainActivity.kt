package com.example.shualedurikotlinn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shualedurikotlinn2.adapter.RecycleViewAdapter
import com.example.shualedurikotlinn2.api.PostApi
import com.example.shualedurikotlinn2.api.RetrofitClient
//import com.example.shualedurikotlinn2.api.ReqResData
import com.example.shualedurikotlinn2.room.App
import com.example.shualedurikotlinn2.room.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecycleViewAdapter
    private lateinit var posts: List<Post>
    private lateinit var mainRecyclerView: RecyclerView;
    private lateinit var textView:TextView;

    private lateinit var swipeRefresh :SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


       // App.instance.db.getPostDao().deleteAllPosts()
        swipeRefresh = findViewById(R.id.swipeRefresh)
        mainRecyclerView = findViewById(R.id.mainRecycle)
        textView = findViewById(R.id.textView)


        if (App.instance.db.getPostDao().getAllPosts().isEmpty()) {
                //wesivrad ver gaverkvie kotlinis async await-shi wesit minda ro daelodos sanam moxdeba
                // getPosts metodi da mere chaweros
                getPosts()

        }

        posts = App.instance.db.getPostDao().getAllPosts()


        textView.text = posts.size.toString()

        recyclerViewAdapter = RecycleViewAdapter(posts)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = recyclerViewAdapter

        swipeRefresh.setOnRefreshListener {
            App.instance.db.getPostDao().deleteAllPosts()

            getPosts()



            swipeRefresh.isRefreshing = false
        }


    }


    private fun getPosts()
        {

            RetrofitClient.reqResApi.getPosts().enqueue(
                object : Callback<List<PostApi>> {
                    override fun onResponse(
                        call: Call<List<PostApi>>,
                        response: Response<List<PostApi>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.forEach { postApi ->
                                App.instance.db.getPostDao().insert(
                                    Post(
                                        postApi.postId,
                                        postApi.userId,
                                        postApi.body,
                                        postApi.title
                                    )
                                )
                            }
                            recyclerViewAdapter.setUpdatedData(App.instance.db.getPostDao().getAllPosts())

                        }
                    }

                    override fun onFailure(call: Call<List<PostApi>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }



    }
