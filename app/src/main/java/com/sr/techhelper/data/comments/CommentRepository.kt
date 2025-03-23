package com.sr.techhelper.data.comments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sr.techhelper.data.posts.PostDTO
import com.sr.techhelper.data.users.UsersRepository
import com.sr.techhelper.room.DatabaseHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CommentsRepository {
    private val usersRepository = UsersRepository()
    private val commentDao = DatabaseHolder.getDatabase().commentDao()
    private val postDao = DatabaseHolder.getDatabase().postDao()
    private val userDao = DatabaseHolder.getDatabase().usersDao()
    private val firestoreHandle = Firebase.firestore.collection("comments")

    fun getAllComments(): LiveData<List<CommentWithSender>> {
        val liveData = commentDao.getAllComments()
        return liveData

//        firestoreHandle.get()
//            .addOnSuccessListener { documents ->
//                Log.d("CommentsRepository", "Fetched comments from Firestore: ${documents.size()}")
//                val comments = mutableListOf<CommentModel>()
//                for (document in documents) {
//                    val comment = CommentModel.fromMap(document.data)
//                    comments.add(comment)
//                    Log.d("CommentsRepository", "Firestore comment: ${comment.content}, PostId: ${comment.postId}")
//                }
//
//                // Insert fetched comments into Room DB
//                CoroutineScope(Dispatchers.IO).launch {
//                    Log.d("CommentsRepository", "Fetchedd comments: $comments")
//                    comments.forEach { comment ->
//                        CoroutineScope(Dispatchers.IO).launch {
//                            val existingUser = userDao.getUserByUid(comment.userId);
//                            val existingPost = postDao.getDataById(comment.postId);
////                            Log.d("CommentsRepository", "Existing User: $existingUser, Existing Post: $existingPost")
//
//                            if (existingUser != null && existingPost != null) {
//                                Log.d("CommentsRepository", "adding a comment: ${comment.content}")
//
//                                commentDao.add(comment)
//                            }
//                        }
//                    }
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.e("CommentsRepository", "Error fetching comments from Firestore", e)
//            }

        return liveData
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
                usersRepository.cacheUsersIfNotExisting(comments.map { it.userId })
                commentDao.upsertAll(*comments.toTypedArray())
            }
        }
    fun deleteAll() {
        commentDao.deleteAll()
    }

}
