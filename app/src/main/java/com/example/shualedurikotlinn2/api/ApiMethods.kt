package com.example.shualedurikotlinn2.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiMethods {

     fun getPost(postId : Long){

        RetrofitClient.reqResApi.getPost(postId).enqueue(object : Callback<PostApi> {
            override fun onResponse(
                call: Call<PostApi>,
                response: Response<PostApi>
            ) {
                if(response.isSuccessful && response!= null){
                    Log.d("mydata",response.body()?.title.toString())
                }else{
                    Log.d("mydata",response.body()?.body.toString())
                }
            }

            override fun onFailure(call: Call<PostApi>, t: Throwable) {
                Log.d("mydata","Shit negro all failed")
            }

        })
    }
}