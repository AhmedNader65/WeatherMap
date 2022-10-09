package com.organization.nytimes.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.organization.nytimes.domain.model.Image


@Entity(tableName = "articles")
class CachedImage(
    @PrimaryKey
    val url: String,
    val articleId: Long,
    val format: String,
    val height: Int,
    val width: Int,
) {
    companion object {
        fun fromDomain(id: Long, image: Image): CachedImage {
            return CachedImage(
                image.url,
                id,
                image.format,
                image.height,
                image.width
            )
        }
    }
}

fun CachedImage.toDomain(): Image {

    return Image(
        url,
        format,
        height,
        width
    )
}