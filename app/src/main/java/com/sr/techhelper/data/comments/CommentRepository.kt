package com.sr.techhelper.data.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sr.techhelper.data.posts.PostDTO
import com.sr.techhelper.data.users.UsersRepository
import com.sr.techhelper.room.DatabaseHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CommentsRepository {
    private val commentDao = DatabaseHolder.getDatabase().commentDao()
    private val firestoreHandle = Firebase.firestore.collection("comments")

    fun getAllComments(): LiveData<List<CommentWithSender>> {
        return commentDao.getAllComments()
    }

    suspend fun add(comment: CommentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(comment.id).set(comment).await()
        commentDao.add(comment)
    }

    suspend fun delete(comment: CommentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(comment.id).delete().await()
        commentDao.delete(comment)
    }

    suspend fun deleteCommentsByPostId(postId: String) = withContext(Dispatchers.IO){
        coroutineScope {
            val comments = commentDao.getCommentsByPostId(postId)
            launch {
                comments.forEach{ comment ->
                    firestoreHandle.document(comment.id).delete().await()
                }
            }

            launch {
                commentDao.deleteByPostId(postId)
            }
        }
    }

    suspend fun loadCommentsFromRemoteSource() =
        withContext(Dispatchers.IO) {
            val comments = firestoreHandle.orderBy("id")
                .get().await().toObjects(CommentDTO::class.java).map { it.toCommentModel() }

            if (comments.isNotEmpty()) {
                commentDao.upsertAll(*comments.toTypedArray())
            }
        }
    fun deleteAll() {
        commentDao.deleteAll()
    }

}
