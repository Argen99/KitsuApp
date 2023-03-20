package com.example.data.remote.model

data class PostsResponseDto(
    val data: List<PostsDataDto>,
    val meta: PostsMetaDto,
    val links: PostsLinksXDto
)

data class PostsLinksXDto(
    val first: String?,
    val next: String?,
    val last: String?
)

data class PostsMetaDto(
    val count: Int?
)

data class PostsDataDto(
    val id: String?,
    val type: String?,
    val links: PostsSelfDto?,
    val attributes: PostsAttributesDto,
    val relationships: PostsRelationshipsDto?,
)

data class PostsRelationshipsDto(
    val user: PostsUserDto?,
    val targetUser: PostsUserDto?,
    val targetGroup: PostsUserDto?,
    val media: PostsUserDto?,
    val spoiledUnit: PostsUserDto?,
    val ama: PostsUserDto?,
    val lockedBy: PostsUserDto?,
    val postLikes: PostsUserDto?,
    val comments: PostsUserDto?,
    val uploads: PostsUserDto?,
    var userModel: UserDto?
)

data class PostsUserDto(
    val links: PostsLinksDto?,
)

data class PostsLinksDto(
    val self: String?,
    val related: String?,
)

data class PostsAttributesDto(
    val createdAt: String?,
    val updatedAt: String?,
    val content: String?,
    val contentFormatted: String?,
    val commentsCount: Int?,
    val postLikesCount: Int?,
    val spoiler: Boolean?,
    val nsfw: Boolean?,
    val blocked: Boolean?,
    val topLevelCommentsCount: Int?,
    val embed: PostsEmbedDto?,
)

data class PostsEmbedDto(
    val url: String?,
    val kind: String?,
    val image: PostsImageDto?,
    val title: String?,
)

data class PostsImageDto(
    val url: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
)

data class PostsSelfDto(
    val self: String?
)
