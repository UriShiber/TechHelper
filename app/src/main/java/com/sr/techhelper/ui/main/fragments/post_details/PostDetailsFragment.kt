package com.sr.techhelper.ui.main.fragments.post_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.ui.main.PostsViewModel
import com.sr.techhelper.utils.ImageUtils

class PostDetailsFragment : Fragment() {
    private val viewModel: PostsViewModel by activityViewModels()
    private var userId: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var postImageView: ImageView
    private lateinit var postTitleTextView: TextView
    private lateinit var postDescriptionTextView: TextView
    private lateinit var postUserNameTextView: TextView
    private lateinit var postLocationLngTextView: TextView
    private lateinit var postLocationLatTextView: TextView
    private lateinit var postTagsTextView: TextView
    private lateinit var editButton: Button
    private lateinit var backButton: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        postImageView = view.findViewById(R.id.post_details_image)
        postTitleTextView = view.findViewById(R.id.post_details_title_text_view)
        postDescriptionTextView = view.findViewById(R.id.post_details_description_text_view)
        postUserNameTextView = view.findViewById(R.id.post_details_username_text_view)
        postTagsTextView = view.findViewById(R.id.post_tags_row)
        postLocationLngTextView = view.findViewById(R.id.post_details_location_lng_text_view)
        postLocationLatTextView = view.findViewById(R.id.post_details_location_lat_text_view)
        editButton = view.findViewById(R.id.edit_button)
        backButton = view.findViewById(R.id.back_button)

        // Get the post ID from arguments
        val postId = arguments?.getString("post_id")

        // Observe the posts LiveData
        viewModel.getAllPosts().observe(viewLifecycleOwner) { posts ->
            if (posts.isEmpty()) viewModel.invalidatePosts()

            // Find the current post
            val currentPost = posts.find { post -> post.post.id == postId }

            // Update the UI with the post data
            currentPost?.let { post ->
                updateUI(post)
            }
        }

        // Set click listeners
        editButton.setOnClickListener {
            val action = PostDetailsFragmentDirections.actionPostDetailsFragmentToEditPostFragment(postId ?: "")
            Navigation.findNavController(it).navigate(action)
        }

        backButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(post: PostWithSender) {
        postTitleTextView.text = post.post.title
        postDescriptionTextView.text = post.post.description
        postUserNameTextView.text = post.sender.name
        val separator = " #"
        postTagsTextView.text = if (post.post.tags.isNullOrEmpty()) "" else "#${post.post.tags?.joinToString(separator)}"
        postLocationLngTextView.text = post.post.locationLng.toString()
        postLocationLatTextView.text = post.post.locationLat.toString()

        if(post.sender.id == userId) {
            editButton.visibility = View.VISIBLE
        }

        post.post.image?.let {
            val bitmap = ImageUtils.decodeBase64ToImage(it)
            postImageView.setImageBitmap(bitmap)
        }
    }
}