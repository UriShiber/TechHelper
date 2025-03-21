package com.sr.techhelper.ui.main.fragments.posts_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.PostsViewModel


class PostsListFragment : Fragment() {
    private lateinit var postsList: RecyclerView
    private val viewModel: PostsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsList = view.findViewById(R.id.posts_list)
        context?.let { initPostsList(it) }
        viewModel.getAllPosts().observe(viewLifecycleOwner) { postsListData ->
            if(postsListData.isEmpty()) viewModel.invalidatePosts()
            (postsList.adapter as? PostsAdapter)?.updatePosts(postsListData)
        }
    }

    private fun initPostsList(context: Context) {
        postsList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = PostsAdapter{ post ->
               val action = PostsListFragmentDirections.actionPostsListFragmentToPostDetailsFragment(post.post.id)
                findNavController().navigate(action)
            }
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
}