package com.example.shualedurikotlinn2.api

import android.util.Log
import com.example.shualedurikotlinn2.room.App
import com.example.shualedurikotlinn2.room.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiMethods {


     suspend fun getPosts() =
         withContext(Dispatchers.Default) {

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

                         }
                     }

                     override fun onFailure(call: Call<List<PostApi>>, t: Throwable) {
                         TODO("Not yet implemented")
                     }

                 })

         }



   suspend fun getPostComment(postId:Long?) : List<CommentApi>?=
       withContext(Dispatchers.Default)
   {
        var returnList:List<CommentApi>? = null;
        RetrofitClient.reqResApi.getPostComment(postId).enqueue(object : Callback<List<CommentApi>>{
            override fun onResponse(
                call: Call<List<CommentApi>>,
                response: Response<List<CommentApi>>
            ) {
                if(response.isSuccessful){

                    returnList = response.body()

                }

            }


            override fun onFailure(call: Call<List<CommentApi>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
       return@withContext returnList
    }


    fun getPost(postId : Long){

        RetrofitClient.reqResApi.getPost(postId).enqueue(object : Callback<PostApi> {
            override fun onResponse(
                call: Call<PostApi>,
                response: Response<PostApi>
            ) {
                if(response.isSuccessful){
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