package com.sr.techhelper.data.posts

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sr.techhelper.data.comments.CommentsRepository
import com.sr.techhelper.data.users.UsersRepository
import com.sr.techhelper.room.DatabaseHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PostsRepository {
    private val postDao = DatabaseHolder.getDatabase().postDao()
    private val usersRepository = UsersRepository()
    private val commentsRepository = CommentsRepository()
    private val firestoreHandle = Firebase.firestore.collection("posts")

    fun getAllPosts(): LiveData<List<PostWithSender>> {
        return postDao.getAllPosts()
    }

    fun getPostsByUserId(userId: String): LiveData<List<PostWithSender>> {
        return postDao.getPostsByUserId(userId)
    }

    suspend fun add(post: PostModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(post.id).set(post).await()
        postDao.add(post)
    }

    suspend fun edit(post: PostModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(post.id).set(post).await()
        postDao.upsertAll(post)
    }

    suspend fun delete(post: PostModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(post.id).delete().await()
        postDao.delete(post)
    }

    suspend fun deleteById(id: String) = withContext(Dispatchers.IO) {
        firestoreHandle.document(id).delete().await()
        postDao.deleteById(id)
    }

    suspend fun loadPostsFromRemoteSource(limit: Int, offset: Int) =
        withContext(Dispatchers.IO) {
            val posts = firestoreHandle.orderBy("id").startAt(offset).limit(limit.toLong())
                .get().await().toObjects(PostDTO::class.java).map { it.toPostModel() }
//            println("delete comments")
//            commentsRepository.deleteAll()
//            println("delete posts")
//            postDao.deleteAll()
//            println("delete users")
//            usersRepository.deleteAll()

            if (posts.isNotEmpty()) {
                usersRepository.cacheUsersIfNotExisting(posts.map { it.userId })
//                postDao.deleteAll()
                postDao.upsertAll(*posts.toTypedArray())
            }
        }
}
