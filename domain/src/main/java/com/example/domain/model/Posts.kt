package com.example.domain.model

data class PostsResponse(
    val data: List<PostsData>,
    val meta: PostsMeta,
    val links: PostsLinksX
)

data class PostsLinksX(
    val first: String?,
    val next: String?,
    val last: String?
)

data class PostsMeta(
    val count: Int?
)

data class PostsData(
    val id: String?,
    val type: String?,
    val links: PostsSelf?,
    val attributes: PostsAttributes,
    val relationships: PostsRelationships?,
)

data class PostsRelationships(
    val user: PostsUser?,
    val targetUser: PostsUser?,
    val targetGroup: PostsUser?,
    val media: PostsUser?,
    val spoiledUnit: PostsUser?,
    val ama: PostsUser?,
    val lockedBy: PostsUser?,
    val postLikes: PostsUser?,
    val comments: PostsUser?,
    val uploads: PostsUser?,
    var userModel: User?
)

data class PostsUser(
    val links: PostsLinks?,
)

data class PostsLinks(
    val self: String?,
    val related: String?,
)

data class PostsAttributes(
    var createdAt: String?,
    val updatedAt: String?,
    var content: String?,
    val contentFormatted: String?,
    val commentsCount: Int?,
    val postLikesCount: Int?,
    val spoiler: Boolean?,
    val nsfw: Boolean?,
    val blocked: Boolean?,
    val topLevelCommentsCount: Int?,
    val embed: PostsEmbed?,
)

data class PostsEmbed(
    val url: String?,
    val kind: String?,
    val image: PostsImage?,
    val title: String?,
)

data class PostsImage(
    val url: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
)

data class PostsSelf(
    val self: String?
)
