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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sr.techhelper.R
import com.sr.techhelper.data.comments.CommentWithSender
import com.sr.techhelper.ui.main.CommentsViewModel
import com.sr.techhelper.ui.main.PostsViewModel

class PostsListFragment : Fragment() {
    private lateinit var postsList: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val postsViewModel: PostsViewModel by activityViewModels()
    private val commentsViewModel: CommentsViewModel by activityViewModels()
    private lateinit var postsAdapter: PostsAdapter
    private var comments = listOf<CommentWithSender>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts_list, container, false)
        swipeRefreshLayout = view.findViewById(R.id.post_list_view)

        swipeRefreshLayout.setOnRefreshListener {
            postsViewModel.getAllPosts()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsList = view.findViewById(R.id.posts_list)
        context?.let { initPostsList(it) }

        // Observe posts from PostsViewModel
        postsViewModel.getAllPosts().observe(viewLifecycleOwner) { posts ->
            if (posts.isEmpty()) postsViewModel.invalidatePosts()
            postsAdapter.updatePosts(posts)
            swipeRefreshLayout.isRefreshing = false // Stop the refreshing animation
        }
        fetchCommentsForPosts()  // Fetch comments for these posts
    }

    private fun fetchCommentsForPosts() {
        commentsViewModel.getAllComments().observe(viewLifecycleOwner) { commentsList ->
            Log.d("PostsAdapter", "Fetching comments: $commentsList")
            comments = commentsList
            postsAdapter.updateComments(comments)
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