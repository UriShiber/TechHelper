package com.sr.techhelper.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sr.techhelper.data.comments.CommentsRepository
import com.google.firebase.auth.FirebaseUser
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.data.posts.PostsRepository
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PostsUiState(val reviewId: String = "")

class PostsViewModel : ViewModel() {
    private val repository = PostsRepository() // Adjusted repository for posts
    private val usersRepository = UsersRepository()
    private val commentsRepository = CommentsRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadPostsFromRemoteSource(50, 0) // Fetch posts from remote source
        }
    }

    fun getAllPosts(): LiveData<List<PostWithSender>> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadPostsFromRemoteSource(50, 0) // Fetch posts from remote source
        }
        return this.repository.getAllPosts() // Fetch all posts from repository
    }

    fun getPostsByUserId(userId: String): LiveData<List<PostWithSender>> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadPostsFromRemoteSource(50, 0) // Fetch posts from remote source
        }
        return this.repository.getPostsByUserId(userId) // Fetch all posts from repository
    }

    fun getUserById(id: String): LiveData<UserModel?> {
        val userLiveData = MutableLiveData<UserModel?>()
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                usersRepository.getUserByUid(id) // Get user by ID for post details
            }
            userLiveData.postValue(user)
        }
        return userLiveData
    }

    fun doUserExist(fireBaseUser: FirebaseUser): LiveData<UserModel?> {
        val userLiveData = MutableLiveData<UserModel?>()
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                usersRepository.getUserByEmail(fireBaseUser.email!!) // Get user by email to check if exists
            }
            userLiveData.postValue(user)
        }
        return userLiveData
    }

    fun updateUser(
        user: UserModel,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            usersRepository.upsertUser(user) // Update user information in the repository
        }
    }

    fun addPost(
        post: PostModel,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.add(post) // Add a new post to the repository
            repository.loadPostsFromRemoteSource(50, 0)
        }
    }

    fun editPost(
        post: PostModel,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.edit(post) // Edit an existing post in the repository
            repository.loadPostsFromRemoteSource(50, 0)
        }
    }

    fun invalidatePosts() {
        viewModelScope.launch {
            repository.loadPostsFromRemoteSource(50, 0) // Reload posts from the remote source
        }
    }

    fun deletePostById(
        postId: String, onComplete: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            commentsRepository.deleteCommentsByPostId(postId) // Delete comments associated with the post
            repository.deleteById(postId) // Delete a post by ID from the repository
            onComplete()
        }
    }

    fun isPostValid(title: String, image: String?, description: String): Boolean {
        return title.isNotBlank() && image != null && image != "" && description.isNotBlank()
    }
}
