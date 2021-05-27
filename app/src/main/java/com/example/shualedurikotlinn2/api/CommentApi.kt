package com.example.shualedurikotlinn2.api

import com.google.gson.annotations.SerializedName

data class CommentApi(
    @SerializedName("postId")
    val postId:Long?,
    @SerializedName("id")
    var userId:Long?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("email")
    var email:String?,
    @SerializedName("body")
    var body:String?
)
