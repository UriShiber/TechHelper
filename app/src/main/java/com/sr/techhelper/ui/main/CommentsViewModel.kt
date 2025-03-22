package com.sr.techhelper.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.comments.CommentWithSender
import com.sr.techhelper.data.comments.CommentsRepository
import kotlinx.coroutines.launch

class CommentsViewModel() : ViewModel() {
    private val repository = CommentsRepository() // Adjusted repository for posts
    fun getAllComments(): LiveData<List<CommentWithSender>> {
        val x = repository.getAllComments()
//        Log.d("CommentsViewModel", "Fetching comments: $x")
        return x
    }

    fun addComment(comment: CommentModel) = viewModelScope.launch {
        repository.add(comment)
    }
}
