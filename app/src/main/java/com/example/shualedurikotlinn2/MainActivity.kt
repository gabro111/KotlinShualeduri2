package com.example.shualedurikotlinn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import com.example.shualedurikotlinn2.api.ApiMethods
import com.example.shualedurikotlinn2.api.PostApi
import com.example.shualedurikotlinn2.api.RetrofitClient
//import com.example.shualedurikotlinn2.api.ReqResData
import com.example.shualedurikotlinn2.room.App
import com.example.shualedurikotlinn2.room.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("mydata","yleo")

        App.instance.db.getPostDao().getAllPosts().forEach {
            Log.d("Nice",it.toString())
        }

        if(App.instance.db.getPostDao().getAllPosts().isEmpty()) {

            getPosts()
        }






    }

    private fun getPosts(){

        RetrofitClient.reqResApi.getPosts().enqueue(
            object: Callback<List<PostApi>> {
            override fun onResponse(call: Call<List<PostApi>>, response: Response<List<PostApi>>) {
                if(response.isSuccessful){

                    response.body()?.forEach { postApi ->
                        App.instance.db.getPostDao().insert(
                            Post(postApi.postId,postApi.userId,postApi.title,postApi.body))
                    }

                }
            }

            override fun onFailure(call: Call<List<PostApi>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }




}