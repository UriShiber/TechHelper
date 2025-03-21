package com.sr.techhelper.ui.main.fragments.posts_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sr.techhelper.R
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.comments.CommentWithSender
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.ui.main.CommentsViewModel
import com.sr.techhelper.ui.main.PostsViewModel

class PostsListFragment : Fragment() {
    private lateinit var postsList: RecyclerView
    private val postsViewModel: PostsViewModel by activityViewModels()
    private val commentsViewModel: CommentsViewModel by activityViewModels()
    private lateinit var postsAdapter: PostsAdapter
    private var comments = listOf<CommentWithSender>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsList = view.findViewById(R.id.posts_list)
        context?.let { initPostsList(it) }

        // Observe posts from PostsViewModel
        postsViewModel.getAllPosts().observe(viewLifecycleOwner) { posts ->
            if (posts.isEmpty()) postsViewModel.invalidatePosts()
            postsAdapter.updatePosts(posts)
            fetchCommentsForPosts(posts)  // Fetch comments for these posts
        }
    }

    private fun fetchCommentsForPosts(posts: List<PostWithSender>) {
        // For each post, get the comments using CommentsViewModel
        posts.forEach { post ->
            commentsViewModel.getCommentsForPost(post.post.id).observe(viewLifecycleOwner) { commentsList ->
                Log.d("PostsAdapter", "Fetching comments: $commentsList")
                comments = commentsList.map { it } // todo: this is due to commentwithsender
                postsAdapter.updateComments(comments)  // Update comments for all posts
            }
        }
    }

    private fun initPostsList(context: Context) {
        postsAdapter = PostsAdapter(
            onPostClick =  { post ->
            val action = PostsListFragmentDirections.actionPostsListFragmentToPostDetailsFragment(post.post.id)
            findNavController().navigate(action)
        },
        onCommentSubmit = { comment ->
            commentsViewModel.addComment(comment)  // Use the ViewModel to add the comment
        })

        postsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }
}
