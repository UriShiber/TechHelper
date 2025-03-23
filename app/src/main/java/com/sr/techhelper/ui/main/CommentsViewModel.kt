package com.sr.techhelper.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.comments.CommentWithSender
import com.sr.techhelper.data.comments.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentsViewModel() : ViewModel() {
    private val repository = CommentsRepository() // Adjusted repository for posts
    fun getAllComments(): LiveData<List<CommentWithSender>> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadCommentsFromRemoteSource() // Fetch posts from remote source
        }
        return repository.getAllComments()
    }

    fun addComment(comment: CommentModel) = viewModelScope.launch {
        repository.add(comment)
    }
}
