import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sr.techhelper.R
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.comments.CommentWithSender
import com.sr.techhelper.utils.ImageUtils
import java.util.Date

class CommentsAdapter(private val comments: List<CommentWithSender>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflator = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_list_item, parent, false)  // Inflate the updated layout
        return CommentViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.comment_user_name)
        private val commentTextView: TextView = itemView.findViewById(R.id.comment_content)
        private val userImageView: ImageView = itemView.findViewById(R.id.comment_user_image)
        private val timestamp: TextView = itemView.findViewById(R.id.timestamp)

        fun bind(comment: CommentWithSender) {
            // Bind the data to the views
            userNameTextView.text = comment.sender.name // change to withSender and use username
            commentTextView.text = comment.comment.content
            if (!comment.sender.profile_picture.isNullOrEmpty()) {
                val bitmap = ImageUtils.decodeBase64ToImage(comment.sender.profile_picture)
                userImageView.setImageBitmap(bitmap)
            } else {
                userImageView.setImageResource(R.drawable.empty_profile_picture)
            }
            val timestampMillis = comment.comment.timestamp // Ensure it's in milliseconds
            val relativeTime = DateUtils.getRelativeTimeSpanString(timestampMillis, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
            timestamp.text = relativeTime

            // Load the user profile image using Glide (if you have a URL or resource)
//            Glide.with(itemView.context) // consider using glide in other places as well
//                .load("comment.userId") // change to withSender and use image
//                .placeholder(R.drawable.default_user) // Placeholder in case of no image
//                .into(userImageView)
        }
    }
}
