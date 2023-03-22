package com.example.kitsuapp.model

data class PostsResponseUI(
    val data: List<PostsDataUI>,
    val meta: PostsMetaUI,
    val links: PostsLinksXUI
)

data class PostsLinksXUI(
    val first: String?,
    val next: String?,
    val last: String?
)

data class PostsMetaUI(
    val count: Int?
)

data class PostsDataUI(
    val id: String?,
    val type: String?,
    val links: PostsSelfUI?,
    val attributes: PostsAttributesUI,
    val relationships: PostsRelationshipsUI?
)

data class PostsRelationshipsUI(
    val user: PostsUserUI?,
    val targetUser: PostsUserUI?,
    val targetGroup: PostsUserUI?,
    val media: PostsUserUI?,
    val spoiledUnit: PostsUserUI?,
    val ama: PostsUserUI?,
    val lockedBy: PostsUserUI?,
    val postLikes: PostsUserUI?,
    val comments: PostsUserUI?,
    val uploads: PostsUserUI?,
    val userModel: UserUI?
)

data class PostsUserUI(
    val links: PostsLinksUI?,
)

data class PostsLinksUI(
    val self: String?,
    val related: String?,
)

data class PostsAttributesUI(
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
    val embed: PostsEmbedUI?,
)

data class PostsEmbedUI(
    val url: String?,
    val kind: String?,
    val image: PostsImageUI?,
    val title: String?,
)

data class PostsImageUI(
    val url: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
)

data class PostsSelfUI(
    val self: String?
)
