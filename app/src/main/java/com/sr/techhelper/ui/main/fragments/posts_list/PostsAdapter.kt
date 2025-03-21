package com.sr.techhelper.ui.main.fragments.posts_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.utils.ImageUtils


class PostsAdapter(
    private val onPostClick: (PostWithSender) -> Unit,
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private var posts: List<PostWithSender> = emptyList()

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

        fun bind(post: PostWithSender) {
            userName.text = post.sender.name
            Log.d("PostsAdapter", "Binding post with  profile picture: ${post.sender.profile_picture}")
            if (post.sender.profile_picture != null) {
                Log.d("PostsAdapter", "profile picture is not null")
                userImage.setImageBitmap(ImageUtils.decodeBase64ToImage(post.sender.profile_picture))
            }


            postTitle.text = post.post.title
            postContent.text = post.post.description

            post.post.image?.let {
                val bitmap = ImageUtils.decodeBase64ToImage(it)
                postImage.setImageBitmap(bitmap)
            }

            postLocationLat.text = "Lat: ${post.post.locationLat}"
            postLocationLng.text = "Lng: ${post.post.locationLng}"

            itemView.setOnClickListener {
                onPostClick(post)
            }
        }
    }

    fun updatePosts(newPosts: List<PostWithSender>) {
        this.posts = newPosts
        notifyDataSetChanged()
    }
}
