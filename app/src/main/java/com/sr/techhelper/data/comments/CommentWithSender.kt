package com.sr.techhelper.data.comments

import androidx.room.Embedded
import androidx.room.Relation
import com.sr.techhelper.data.users.UserModel

data class CommentWithSender(
    @Embedded val comment: CommentModel,
    @Relation(
        entity = UserModel::class,
        parentColumn = "userId",
        entityColumn = "id"
    ) val sender: UserModel
)