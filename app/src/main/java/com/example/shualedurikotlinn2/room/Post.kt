package com.example.shualedurikotlinn2.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "POSTS")
data class Post(
    @PrimaryKey
    @ColumnInfo(name = "POST_ID")
    var postId: Long?,

    @ColumnInfo(name = "USER_ID")
    var userId: Long?,

    @ColumnInfo(name = "BODY")
    var body: String?,

    @ColumnInfo(name = "TITLE")
    var title: String?
)