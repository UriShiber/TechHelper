package com.sr.techhelper.ui.main.fragments.posts_list

import CommentsAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.utils.ImageUtils
import java.util.Date


class PostsAdapter(
    private val onPostClick: (PostWithSender) -> Unit,
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private var posts: List<PostWithSender> = emptyList()
    private var comments = listOf<CommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflator = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)  // Inflate the updated layout
        return PostViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        val postComments = comments.filter { it.postId == post.post.id }  // Filter comments by postId
        holder.bind(post, postComments)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentUserUid: String = FirebaseAuth.getInstance().currentUser!!.uid

        private val commentsRecyclerView: RecyclerView = itemView.findViewById(R.id.comments_recycler_view)
        val addCommentEditText = itemView.findViewById<EditText>(R.id.add_comment_edit_text)
        val addCommentsubmitButton = itemView.findViewById<Button>(R.id.submit_comment_button)

        private val userName: TextView = itemView.findViewById(R.id.user_name_text_view)
        private val userImage: ImageView = itemView.findViewById(R.id.user_image_view)
        private val postTitle: TextView = itemView.findViewById(R.id.post_row_title_text_view)
        private val postContent: TextView = itemView.findViewById(R.id.post_row_content_text_view)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image_view)
        private val postLocationLat: TextView = itemView.findViewById(R.id.post_row_location_lat_text_view)
        private val postLocationLng: TextView = itemView.findViewById(R.id.post_row_location_lng_text_view)

        fun bind(post: PostWithSender, postComments: List<CommentModel>) {
            userName.text = post.sender.name
            Log.d("PostsAdapter", "Binding post with  profile picture: ${post.sender}")

            if (!post.sender.profile_picture.isNullOrEmpty()) {
                val bitmap = ImageUtils.decodeBase64ToImage(post.sender.profile_picture)
                userImage.setImageBitmap(bitmap)
            } else {
                userImage.setImageResource(R.drawable.empty_profile_picture)
            }


            postTitle.text = post.post.title
            postContent.text = post.post.description

            if (!post.post.image.isNullOrEmpty()) {
                val bitmap = ImageUtils.decodeBase64ToImage(post.post.image)
                postImage.setImageBitmap(bitmap)
            } else {
                postImage.setImageResource(R.drawable.empty_profile_picture)
            }

            postLocationLat.text = "Lat: ${post.post.locationLat}"
            postLocationLng.text = "Lng: ${post.post.locationLng}"

            itemView.setOnClickListener {
                onPostClick(post)
            }

            // Initialize comments RecyclerView
            val commentsAdapter = CommentsAdapter(postComments)
            commentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = commentsAdapter
                addItemDecoration(DividerItemDecoration(itemView.context, LinearLayoutManager.VERTICAL))
            }

            // Set up the event listener for the "Enter" key (submit on Enter key press)
            addCommentEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND) {
                    submitComment(post)
                    true // Indicate that we've handled the event
                } else {
                    false
                }
            }

            // Set up the event listener for the "Submit" button
            addCommentsubmitButton.setOnClickListener {
                submitComment(post)
            }
        }

        // Helper function to handle comment submission
        private fun submitComment(post: PostWithSender) {
            val commentText = addCommentEditText.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // Create and submit the comment (you would need to implement this part)
                val newComment = CommentModel(
                    timestamp = Date().time,
                    postId = post.post.id,
                    content = commentText,
                    userId = currentUserUid // Replace with actual current user ID
                )

                // Call the ViewModel's addComment function to insert the comment into the database
                commentsViewModel.addComment(newComment)

                // Optionally, update the UI or refresh comments
                commentsViewModel.getCommentsForPost(post.post.id).observeForever { updatedComments ->
                    // Update the comment list with new data
                    updateComments(updatedComments.map { it.comment })
                }

                // Clear the input field after submission
                addCommentEditText.text.clear()
            }
        }
    }

    fun updatePosts(newPosts: List<PostWithSender>) {
        this.posts = newPosts
        notifyDataSetChanged()
    }

    fun updateComments(newComments: List<CommentModel>) {
        this.comments = newComments
        Log.d("PostsAdapter", "Updating comments: $comments")
        notifyDataSetChanged()
    }
}
