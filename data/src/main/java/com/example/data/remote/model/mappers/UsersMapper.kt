package com.example.data.remote.model.mappers

import com.example.data.remote.model.*
import com.example.domain.model.*

fun UsersResponse.toDto() = UsersResponseDto(
    data = data.map { it.toDto() },
    meta = meta.toDto(),
    links = links?.toDto()
)

fun UserResponse.toDto() = UserResponseDto(
    data = data.toDto()
)

fun UserCountMeta.toDto() = UserCountMetaDto(
    count = count
)

fun UsLinks.toDto() = UsLinksDto(
    first = first,
    next = next,
    last = last
)

fun User.toDto() = UserDto(
    id = id,
    type = type,
    links = links?.toDto(),
    attributes = attributes?.toDto(),
    relationships = relationships?.toDto()
)

fun UserAttributes.toDto() = UserAttributesDto(
    createdAt = createdAt,
    updatedAt = updatedAt,
    name = name,
    pastNames = pastNames,
    slug = slug,
    about = about,
    location = location,
    waifuOrHusbando = waifuOrHusbando,
    followersCount = followersCount,
    followingCount = followingCount,
    lifeSpentOnAnime = lifeSpentOnAnime,
    birthday = birthday,
    gender = gender,
    commentsCount = commentsCount,
    favoritesCount = favoritesCount,
    likesGivenCount = likesGivenCount,
    reviewsCount = reviewsCount,
    likesReceivedCount = likesReceivedCount,
    postsCount = postsCount,
    ratingsCount = ratingsCount,
    mediaReactionsCount = mediaReactionsCount,
    proExpiresAt = proExpiresAt,
    title = title,
    profileCompleted = profileCompleted,
    feedCompleted = feedCompleted,
    website = website,
    proTier = proTier,
    avatar = avatar?.toDto(),
    coverImage = coverImage?.toDto(),
    status = status,
    subscribedToNewsletter = subscribedToNewsletter,
    permissions = permissions,
    sfwFilterPreference = sfwFilterPreference
)

fun UserRelationships.toDto() = UserRelationshipsDto(
    waifu = waifu?.toDto(),
    pinnedPost = pinnedPost?.toDto(),
    followers = followers?.toDto(),
    following = following?.toDto(),
    blocks = blocks?.toDto(),
    linkedAccounts = linkedAccounts?.toDto(),
    profileLinks = profileLinks?.toDto(),
    userRoles = userRoles?.toDto(),
    libraryEntries = libraryEntries?.toDto(),
    favorites = favorites?.toDto(),
    reviews = reviews?.toDto(),
    stats = stats?.toDto(),
    notificationSettings = notificationSettings?.toDto(),
    oneSignalPlayers = oneSignalPlayers?.toDto(),
    categoryFavorites = categoryFavorites?.toDto(),
    quotes = quotes?.toDto()
)

fun ULinks.toDto() = ULinksDto(
    links = links?.toDto()
)

fun UserLinks.toDto() = UserLinksDto(
    self = self,
    related = related
)

fun UserCoverImage.toDto() = UserCoverImageDto(
    tiny = tiny,
    large = large,
    small = small,
    original = original,
    meta = meta?.toDto()
)

fun UserAvatar.toDto() = UserAvatarDto(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toDto()
)

fun UserMeta.toDto() = UserMetaDto(
    dimensions = dimensions?.toDto()
)

fun UserDimensions.toDto() = UserDimensionsDto(
    tiny = tiny?.toDto(),
    large = large?.toDto(),
    small = small?.toDto(),
    medium = medium?.toDto()
)

fun UDimensions.toDto() = UDimensionsDto(
    width = width,
    height = height
)

//  **** To Model ****

fun UsersResponseDto.toModel() = UsersResponse(
    data = data.map { it.toModel() },
    meta = meta.toModel(),
    links = links?.toModel()
)

fun UserResponseDto.toModel() = UserResponse(
    data = data.toModel()
)

fun UserCountMetaDto.toModel() = UserCountMeta(
    count = count
)

fun UsLinksDto.toModel() = UsLinks(
    first = first,
    next = next,
    last = last
)

fun UserDto.toModel() = User(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes?.toModel(),
    relationships = relationships?.toModel()
)

fun UserAttributesDto.toModel() = UserAttributes(
    createdAt = createdAt,
    updatedAt = updatedAt,
    name = name,
    pastNames = pastNames,
    slug = slug,
    about = about,
    location = location,
    waifuOrHusbando = waifuOrHusbando,
    followersCount = followersCount,
    followingCount = followingCount,
    lifeSpentOnAnime = lifeSpentOnAnime,
    birthday = birthday,
    gender = gender,
    commentsCount = commentsCount,
    favoritesCount = favoritesCount,
    likesGivenCount = likesGivenCount,
    reviewsCount = reviewsCount,
    likesReceivedCount = likesReceivedCount,
    postsCount = postsCount,
    ratingsCount = ratingsCount,
    mediaReactionsCount = mediaReactionsCount,
    proExpiresAt = proExpiresAt,
    title = title,
    profileCompleted = profileCompleted,
    feedCompleted = feedCompleted,
    website = website,
    proTier = proTier,
    avatar = avatar?.toModel(),
    coverImage = coverImage?.toModel(),
    status = status,
    subscribedToNewsletter = subscribedToNewsletter,
    permissions = permissions,
    sfwFilterPreference = sfwFilterPreference
)

fun UserRelationshipsDto.toModel() = UserRelationships(
    waifu = waifu?.toModel(),
    pinnedPost = pinnedPost?.toModel(),
    followers = followers?.toModel(),
    following = following?.toModel(),
    blocks = blocks?.toModel(),
    linkedAccounts = linkedAccounts?.toModel(),
    profileLinks = profileLinks?.toModel(),
    userRoles = userRoles?.toModel(),
    libraryEntries = libraryEntries?.toModel(),
    favorites = favorites?.toModel(),
    reviews = reviews?.toModel(),
    stats = stats?.toModel(),
    notificationSettings = notificationSettings?.toModel(),
    oneSignalPlayers = oneSignalPlayers?.toModel(),
    categoryFavorites = categoryFavorites?.toModel(),
    quotes = quotes?.toModel()
)

fun ULinksDto.toModel() = ULinks(
    links = links?.toModel()
)

fun UserLinksDto.toModel() = UserLinks(
    self = self,
    related = related
)

fun UserCoverImageDto.toModel() = UserCoverImage(
    tiny = tiny,
    large = large,
    small = small,
    original = original,
    meta = meta?.toModel(),
)

fun UserAvatarDto.toModel() = UserAvatar(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toModel()
)

fun UserMetaDto.toModel() = UserMeta(
    dimensions = dimensions?.toModel()
)

fun UserDimensionsDto.toModel() = UserDimensions(
    tiny = tiny?.toModel(),
    large = large?.toModel(),
    small = small?.toModel(),
    medium = medium?.toModel()
)

fun UDimensionsDto.toModel() = UDimensions(
    width = width,
    height = height
)