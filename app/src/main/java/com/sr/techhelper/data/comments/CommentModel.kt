package com.sr.techhelper.data.comments

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sr.techhelper.data.posts.PostModel
import java.util.UUID

@Entity(tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = PostModel::class,
        parentColumns = ["id"],
        childColumns = ["postId"]
    )])
data class CommentModel(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val postId: String,
    val userId: String,
    val content: String,
    val timestamp: Long
)
