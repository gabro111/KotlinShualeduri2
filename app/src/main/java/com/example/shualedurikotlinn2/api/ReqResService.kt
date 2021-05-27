package com.example.shualedurikotlinn2.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {

    @GET("posts")
    fun getPosts(): Call<List<PostApi>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Long):Call<PostApi>

    @GET("posts/{postId}/comments")
    fun getPostComment(@Path("postId") postId:Long?):Call<List<CommentApi>>
}