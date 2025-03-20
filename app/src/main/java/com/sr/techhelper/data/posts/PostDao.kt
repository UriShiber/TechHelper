package com.sr.techhelper.data.posts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostModel>>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getById(id: String): LiveData<PostModel>

    @Query("SELECT * FROM posts WHERE userId = :userId")
    fun getPostsByUserId(userId: String): LiveData<List<PostModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg post: PostModel)

    @Update
    fun update(post: PostModel)

    @Delete
    fun delete(post: PostModel)

    @Query("DELETE FROM posts WHERE id = :id")
    fun deleteById(id: String)

    @Upsert
    fun upsertAll(vararg post: PostModel)
}
