package com.example.kitsuapp.model.mappers

import com.example.domain.model.*
import com.example.kitsuapp.model.*

fun PostsResponse.toUI() = PostsResponseUI(
    data = data.map { it.toUI() },
    meta = meta.toUI(),
    links = links.toUI()
)

fun PostsLinksX.toUI() = PostsLinksXUI(
    first = first,
    next = next,
    last = last
)

fun PostsMeta.toUI() = PostsMetaUI(
    count = count
)

fun PostsData.toUI() = PostsDataUI(
    id = id,
    type = type,
    links = links?.toUI(),
    attributes = attributes.toUI(),
    relationships = relationships?.toUI()
)

fun PostsRelationships.toUI() = PostsRelationshipsUI(
    user = user?.toUI(),
    targetUser = targetUser?.toUI(),
    targetGroup = targetGroup?.toUI(),
    media = media?.toUI(),
    spoiledUnit = spoiledUnit?.toUI(),
    ama = ama?.toUI(),
    lockedBy = lockedBy?.toUI(),
    postLikes = postLikes?.toUI(),
    comments = comments?.toUI(),
    uploads = uploads?.toUI(),
    userModel = userModel?.toUI()
)

fun PostsUser.toUI() = PostsUserUI(
    links = links?.toUI()
)

fun PostsLinks.toUI() = PostsLinksUI(
    self = self,
    related = related
)

fun PostsAttributes.toUI() = PostsAttributesUI(
    createdAt = createdAt,
    updatedAt = updatedAt,
    content = content,
    contentFormatted = contentFormatted,
    commentsCount = commentsCount,
    postLikesCount = postLikesCount,
    spoiler = spoiler,
    nsfw = nsfw,
    blocked = blocked,
    topLevelCommentsCount = topLevelCommentsCount,
    embed = embed?.toUI()
)

fun PostsEmbed.toUI() = PostsEmbedUI(
    url = url,
    kind = kind,
    image = image?.toUI(),
    title = title
)

fun PostsImage.toUI() = PostsImageUI(
    url = url,
    type = type,
    width = width,
    height = height
)
fun PostsSelf.toUI() = PostsSelfUI(
    self = self
)