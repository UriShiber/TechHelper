package com.sr.techhelper.data.users

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.utils.ImageUtils

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey val id: String = "",
    val name: String,
    val email: String,
    val profile_picture: String
) {
    fun toUserDto() : UserDTO {
        return UserDTO(
            id = id,
            name = name,
            email = email,
            profilePicture = profile_picture
        )
    }
    companion object {
        fun fromFirebaseAuth(userImage: String): UserModel {
            val user = FirebaseAuth.getInstance().currentUser

            return UserModel(
                id = user?.uid!!,
                email = user.email!!,
                name = user.displayName!!,
                profile_picture = userImage
            )
        }
    }
}
