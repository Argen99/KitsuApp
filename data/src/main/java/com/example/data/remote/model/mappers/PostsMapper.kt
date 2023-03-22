package com.example.data.remote.model.mappers

import com.example.data.remote.model.*
import com.example.domain.model.*

fun PostsResponseDto.toModel() = PostsResponse(
    data = data.map { it.toModel() },
    meta = meta.toModel(),
    links = links.toModel()
)

fun PostsLinksXDto.toModel() = PostsLinksX(
    first = first,
    next = next,
    last = last
)

fun PostsMetaDto.toModel() = PostsMeta(
    count = count
)

fun PostsDataDto.toModel() = PostsData(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes.toModel(),
    relationships = relationships?.toModel()
)

fun PostsRelationshipsDto.toModel() = PostsRelationships(
    user = user?.toModel(),
    targetUser = targetUser?.toModel(),
    targetGroup = targetGroup?.toModel(),
    media = media?.toModel(),
    spoiledUnit = spoiledUnit?.toModel(),
    ama = ama?.toModel(),
    lockedBy = lockedBy?.toModel(),
    postLikes = postLikes?.toModel(),
    comments = comments?.toModel(),
    uploads = uploads?.toModel(),
    userModel = userModel?.toModel()
)

fun PostsUserDto.toModel() = PostsUser(
    links = links?.toModel()
)

fun PostsLinksDto.toModel() = PostsLinks(
    self = self,
    related = related
)

fun PostsAttributesDto.toModel() = PostsAttributes(
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
    embed = embed?.toModel()
)

fun PostsEmbedDto.toModel() = PostsEmbed(
    url = url,
    kind = kind,
    image = image?.toModel(),
    title = title
)

fun PostsImageDto.toModel() = PostsImage(
    url = url,
    type = type,
    width = width,
    height = height
)

fun PostsSelfDto.toModel() = PostsSelf(
    self = self
)

// to DTO ***

fun PostsResponse.toDto() = PostsResponseDto(
    data = data.map { it.toDto() },
    meta = meta.toDto(),
    links = links.toDto()
)

fun PostsLinksX.toDto() = PostsLinksXDto(
    first = first,
    next = next,
    last = last
)

fun PostsMeta.toDto() = PostsMetaDto(
    count = count
)

fun PostsData.toDto() = PostsDataDto(
    id = id,
    type = type,
    links = links?.toDto(),
    attributes = attributes.toDto(),
    relationships = relationships?.toDto()
)

fun PostsRelationships.toDto() = PostsRelationshipsDto(
    user = user?.toDto(),
    targetUser = targetUser?.toDto(),
    targetGroup = targetGroup?.toDto(),
    media = media?.toDto(),
    spoiledUnit = spoiledUnit?.toDto(),
    ama = ama?.toDto(),
    lockedBy = lockedBy?.toDto(),
    postLikes = postLikes?.toDto(),
    comments = comments?.toDto(),
    uploads = uploads?.toDto(),
    userModel = userModel?.toDto()
)

fun PostsUser.toDto() = PostsUserDto(
    links = links?.toDto()
)

fun PostsLinks.toDto() = PostsLinksDto(
    self = self,
    related = related
)

fun PostsAttributes.toDto() = PostsAttributesDto(
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
    embed = embed?.toDto()
)

fun PostsEmbed.toDto() = PostsEmbedDto(
    url = url,
    kind = kind,
    image = image?.toDto(),
    title = title
)

fun PostsImage.toDto() = PostsImageDto(
    url = url,
    type = type,
    width = width,
    height = height
)

fun PostsSelf.toDto() = PostsSelfDto(
    self = self
)
