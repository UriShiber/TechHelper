package com.sr.techhelper.data.comments

data class CommentDTO(
    val id: String,
    val postId: String,
    val userId: String,
    val content: String,
    val timestamp: Long
) {
    
}
