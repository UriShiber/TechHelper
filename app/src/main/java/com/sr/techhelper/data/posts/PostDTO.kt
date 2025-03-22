package com.sr.techhelper.data.posts

data class PostDTO(
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    val userId: String = "",
    val locationLng: Double = 0.0,
    val locationLat: Double = 0.0,
    val image: String? = null,
    val tags: List<String>? = null
) {
    fun toPostModel(): PostModel {
        return PostModel(
            id = id ?: "",
            title = title,
            description = description,
            userId = userId,
            locationLng = locationLng,
            locationLat = locationLat,
            image = image,
//            tags = tags
        )
    }
}
