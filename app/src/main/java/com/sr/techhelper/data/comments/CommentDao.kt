package com.sr.techhelper.data.comments

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sr.techhelper.data.posts.PostModel

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments ORDER BY timestamp ASC")
    fun getAllComments(): LiveData<List<CommentWithSender>>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    suspend fun getCommentsByPostId(postId: String): List<CommentModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg comment: CommentModel)

    @Delete
    fun delete(comment: CommentModel)

    @Query("DELETE FROM comments WHERE postId = :postId")
    suspend fun deleteByPostId(postId: String)

    @Upsert
    fun upsertAll(vararg comments: CommentModel)

    @Query("DELETE FROM comments")
    fun deleteAll()
}
