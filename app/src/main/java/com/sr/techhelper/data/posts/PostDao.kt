package com.sr.techhelper.data.posts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY timestamp DESC")
    fun getAllPosts(): LiveData<List<PostWithSender>>

    @Query("SELECT * FROM posts")
    suspend fun getAllPostsOnce(): List<PostWithSender>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getById(id: String): LiveData<PostWithSender?>

    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getDataById(id: String): PostWithSender?

    @Query("SELECT * FROM posts WHERE userId = :userId ORDER BY timestamp DESC")
    fun getPostsByUserId(userId: String): LiveData<List<PostWithSender>>

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

    @Query("DELETE FROM posts")
    fun deleteAll()
}
