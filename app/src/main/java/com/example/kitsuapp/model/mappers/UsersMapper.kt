package com.example.kitsuapp.model.mappers

import com.example.domain.model.*
import com.example.kitsuapp.model.*

fun UserResponse.toUI() = UserResponseUI(
    data = data.map { it.toUI() },
    meta = meta.toUI(),
    links = links?.toUI()
)

fun UserCountMeta.toUI() = UserCountMetaUI(
    count = count
)

fun UsLinks.toUI() = UsLinksUI(
    first = first,
    next = next,
    last = last
)

fun User.toUI() = UserUI(
    id = id,
    type = type,
    links = links?.toUI(),
    attributes = attributes?.toUI(),
    relationships = relationships?.toUI()
)

fun UserAttributes.toUI() = UserAttributesUI(
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
    avatar = avatar?.toUI(),
    coverImage = coverImage?.toUI(),
    status = status,
    subscribedToNewsletter = subscribedToNewsletter,
    permissions = permissions,
    sfwFilterPreference = sfwFilterPreference
)

fun UserRelationships.toUI() = UserRelationshipsUI(
    waifu = waifu?.toUI(),
    pinnedPost = pinnedPost?.toUI(),
    followers = followers?.toUI(),
    following = following?.toUI(),
    blocks = blocks?.toUI(),
    linkedAccounts = linkedAccounts?.toUI(),
    profileLinks = profileLinks?.toUI(),
    userRoles = userRoles?.toUI(),
    libraryEntries = libraryEntries?.toUI(),
    favorites = favorites?.toUI(),
    reviews = reviews?.toUI(),
    stats = stats?.toUI(),
    notificationSettings = notificationSettings?.toUI(),
    oneSignalPlayers = oneSignalPlayers?.toUI(),
    categoryFavorites = categoryFavorites?.toUI(),
    quotes = quotes?.toUI()
)

fun ULinks.toUI() = ULinksUI(
    links = links?.toUI()
)

fun UserLinks.toUI() = UserLinksUI(
    self = self,
    related = related
)

fun UserCoverImage.toUI() = UserCoverImageUI(
    tiny = tiny,
    large = large,
    small = small,
    original = original,
    meta = meta?.toUI()
)

fun UserAvatar.toUI() = UserAvatarUI(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toUI()
)

fun UserMeta.toUI() = UserMetaUI(
    dimensions = dimensions?.toUI()
)

fun UserDimensions.toUI() = UserDimensionsUI(
    tiny = tiny?.toUI(),
    large = large?.toUI(),
    small = small?.toUI(),
    medium = medium?.toUI()
)

fun UDimensions.toUI() = UDimensionsUI(
    width = width,
    height = height
)

//  **** To Model ****

fun UserResponseUI.toModel() = UserResponse(
    data = data.map { it.toModel() },
    meta = meta.toModel(),
    links = links?.toModel()
)

fun UserCountMetaUI.toModel() = UserCountMeta(
    count = count
)

fun UsLinksUI.toModel() = UsLinks(
    first = first,
    next = next,
    last = last
)

fun UserUI.toModel() = User(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes?.toModel(),
    relationships = relationships?.toModel()
)

fun UserAttributesUI.toModel() = UserAttributes(
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

fun UserRelationshipsUI.toModel() = UserRelationships(
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

fun ULinksUI.toModel() = ULinks(
    links = links?.toModel()
)

fun UserLinksUI.toModel() = UserLinks(
    self = self,
    related = related
)

fun UserCoverImageUI.toModel() = UserCoverImage(
    tiny = tiny,
    large = large,
    small = small,
    original = original,
    meta = meta?.toModel(),
)

fun UserAvatarUI.toModel() = UserAvatar(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toModel()
)

fun UserMetaUI.toModel() = UserMeta(
    dimensions = dimensions?.toModel()
)

fun UserDimensionsUI.toModel() = UserDimensions(
    tiny = tiny?.toModel(),
    large = large?.toModel(),
    small = small?.toModel(),
    medium = medium?.toModel()
)

fun UDimensionsUI.toModel() = UDimensions(
    width = width,
    height = height
)