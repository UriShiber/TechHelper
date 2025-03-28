package com.sr.techhelper.data.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.sr.techhelper.data.StringListConverters
import com.sr.techhelper.data.users.UserModel
import java.util.UUID

@Entity(tableName = "posts",
    foreignKeys = [ForeignKey(
        entity = UserModel::class,
        parentColumns = ["id"],
        childColumns = ["userId"]
    )])
@TypeConverters(StringListConverters::class)
data class PostModel(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "locationLng") val locationLng: Double,
    @ColumnInfo(name = "locationLat") val locationLat: Double,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "tags") val tags: List<String>? = null,
    @ColumnInfo(name= "timestamp") val timestamp: Long = System.currentTimeMillis()
) {
    fun toPostDto(): PostDTO {
        return PostDTO(
            id = id,
            title = title,
            description = description,
            userId = userId,
            locationLng = locationLng,
            locationLat = locationLat,
            image = image,
            timestamp = timestamp,
            tags = tags
        )
    }
}
