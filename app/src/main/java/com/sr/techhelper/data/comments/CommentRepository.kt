package com.sr.techhelper.data.comments

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sr.techhelper.data.users.UsersRepository
import com.sr.techhelper.room.DatabaseHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CommentsRepository {
    private val commentDao = DatabaseHolder.getDatabase().commentDao()
    private val usersRepository = UsersRepository()
    private val firestoreHandle = Firebase.firestore.collection("comments")

    fun getAllComments(): LiveData<List<CommentWithSender>> {
        return commentDao.getAllComments()
    }

    suspend fun add(comment: CommentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.add(comment).await()
        commentDao.add(comment)
    }

    suspend fun delete(comment: CommentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(comment.id).delete().await()
        commentDao.delete(comment)
    }
}
