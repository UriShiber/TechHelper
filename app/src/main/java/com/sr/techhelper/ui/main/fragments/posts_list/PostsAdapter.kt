package com.sr.techhelper.ui.main.fragments.posts_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.utils.decodeBase64ToImage

class PostsAdapter(
    private val onPostClick: (PostModel) -> Unit
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private var posts: List<PostModel> = emptyList()
    private var user = FirebaseAuth.getInstance().currentUser!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflator = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)  // Inflate the updated layout
        return PostViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.user_name_text_view)
        private val userImage: ImageView = itemView.findViewById(R.id.user_image_view)
        private val postTitle: TextView = itemView.findViewById(R.id.post_row_title_text_view)
        private val postContent: TextView = itemView.findViewById(R.id.post_row_content_text_view)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image_view)
        private val postLocationLat: TextView = itemView.findViewById(R.id.post_row_location_lat_text_view)
        private val postLocationLng: TextView = itemView.findViewById(R.id.post_row_location_lng_text_view)

        fun bind(post: PostModel) {
            // Set user details
            userName.text = user.displayName
//            post.userImage?.let {
//                val bitmap = decodeBase64ToImage(it)
//                userImage.setImageBitmap(bitmap)
//            }

            // Set post details
            postTitle.text = post.title
            postContent.text = post.description

            post.image?.let {
                val bitmap = decodeBase64ToImage(it)
                postImage.setImageBitmap(bitmap)
            }

            // Set location details
            postLocationLat.text = "Lat: ${post.locationLat}"
            postLocationLng.text = "Lng: ${post.locationLng}"

            itemView.setOnClickListener {
                onPostClick(post)
            }
        }
    }

    fun updatePosts(newPosts: List<PostModel>) {
        this.posts = newPosts
        notifyDataSetChanged()
    }
}
