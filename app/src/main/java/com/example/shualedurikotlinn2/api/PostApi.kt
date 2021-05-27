package com.example.shualedurikotlinn2.api

import com.google.gson.annotations.SerializedName

data class PostApi(
    @SerializedName("id")
    val postId:Long,
    @SerializedName("userId")
    var userId:Long?,
    @SerializedName("title")
    var title:String?,
    @SerializedName("body")
    var body:String?

)






