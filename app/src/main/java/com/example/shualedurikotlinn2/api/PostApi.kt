package com.example.shualedurikotlinn2.api

import com.google.gson.annotations.SerializedName

data class PostApi(
    @SerializedName("id")
    val postId:Long?,
    @SerializedName("userId")
    var userId:Long?,
    @SerializedName("title")
    var title:String?,
    @SerializedName("body")
    var body:String?

)

//data class ReqResData<T>(
//    val postId: Long?,
//    val data: T,
//)



data class CommentApi(
    @SerializedName("postId")
    val postId:Long?,
    @SerializedName("id")
    var userId:Long?,

)
