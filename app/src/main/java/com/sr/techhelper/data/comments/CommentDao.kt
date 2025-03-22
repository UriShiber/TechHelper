package com.sr.techhelper.data.comments

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments ORDER BY timestamp ASC")
    fun getAllComments(): LiveData<List<CommentWithSender>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg comment: CommentModel)

    @Delete
    fun delete(comment: CommentModel)
}
