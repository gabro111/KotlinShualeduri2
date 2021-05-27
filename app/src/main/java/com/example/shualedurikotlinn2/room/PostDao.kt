package com.example.shualedurikotlinn2.room

import androidx.room.*
import com.example.shualedurikotlinn2.api.PostApi


@Dao
interface PostDao {

    @Query("SELECT * FROM POSTS")
    fun getAllPosts():List<Post>

    @Query("Select * From POSTS WHERE POST_ID = :postId")
    fun getPost(postId:Long?):Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg post: Post)

    @Delete
    fun delete(post:Post)

    @Query("DELETE FROM POSTS")
    fun deleteAllPosts()
}