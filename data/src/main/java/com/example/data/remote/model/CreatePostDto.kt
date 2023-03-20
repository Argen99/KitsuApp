package com.example.data.remote.model

data class CreatePostDto(
    val data: CreatePostDataDto
)

data class CreatePostDataDto(
    val attributes: CreatePostAttributesDto,
    val relationships: CreatePostRelationshipsDto,
    var type: String = "posts"
)

data class CreatePostRelationshipsDto(
    val user: CreatePostUserDto,
    val uploads: CreatePostUploadsDto,
)

data class CreatePostUploadsDto(
    val data: List<Any>? = emptyList()
)

data class CreatePostUserDto(
    val data: CreatePostUserDataDto,
)

data class CreatePostUserDataDto(
    val type: String = "users",
    val id: String
)

data class CreatePostAttributesDto(
    val content: String,
    var nsfw: Boolean = false,
    var spoiler: Boolean = false
)
