package com.example.data.remote.model

data class UsersResponseDto(
    val data: List<UserDto>,
    val meta: UserCountMetaDto,
    val links: UsLinksDto?
)

data class UserResponseDto(
    val data: UserDto
)

data class UserCountMetaDto(
    val count: Int
)

data class UsLinksDto(
    val first: String?,
    val next: String?,
    val last: String?
)

data class UserDto(
    val id: String?,
    val type: String?,
    val links: UserLinksDto?,
    val attributes: UserAttributesDto?,
    val relationships: UserRelationshipsDto?
)

data class UserAttributesDto(
    val createdAt: String?,
    val updatedAt: String?,
    val name: String?,
    val pastNames: List<String>?,
    val slug: String?,
    val about: String?,
    val location: String?,
    val waifuOrHusbando: String?,
    val followersCount: Int?,
    val followingCount: Int?,
    val lifeSpentOnAnime: Int?,
    val birthday: String?,
    val gender: String?,
    val commentsCount: Int?,
    val favoritesCount: Int?,
    val likesGivenCount: Int?,
    val reviewsCount: Int?,
    val likesReceivedCount: Int?,
    val postsCount: Int?,
    val ratingsCount: Int?,
    val mediaReactionsCount: Int?,
    val proExpiresAt: String?,
    val title: String?,
    val profileCompleted: Boolean?,
    val feedCompleted: Boolean?,
    val website: String?,
    val proTier: String?,
    val avatar: UserAvatarDto?,
    val coverImage: UserCoverImageDto?,
    val status: String?,
    val subscribedToNewsletter: Boolean?,
    val permissions: String?,
    val sfwFilterPreference: String?
)

data class UserRelationshipsDto(
    val waifu: ULinksDto?,
    val pinnedPost: ULinksDto?,
    val followers: ULinksDto?,
    val following: ULinksDto?,
    val blocks: ULinksDto?,
    val linkedAccounts: ULinksDto?,
    val profileLinks: ULinksDto?,
    val userRoles: ULinksDto?,
    val libraryEntries: ULinksDto?,
    val favorites: ULinksDto?,
    val reviews: ULinksDto?,
    val stats: ULinksDto?,
    val notificationSettings: ULinksDto?,
    val oneSignalPlayers: ULinksDto?,
    val categoryFavorites: ULinksDto?,
    val quotes: ULinksDto?
)

data class ULinksDto(
    val links: UserLinksDto?
)

data class UserLinksDto(
    val self: String?,
    val related: String?
)

data class UserCoverImageDto(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val original: String?,
    val meta: UserMetaDto?
)

data class UserAvatarDto(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: UserMetaDto?
)

data class UserMetaDto(
    val dimensions: UserDimensionsDto?
)

data class UserDimensionsDto(
    val tiny: UDimensionsDto?,
    val large: UDimensionsDto?,
    val small: UDimensionsDto?,
    val medium: UDimensionsDto?
)

data class UDimensionsDto(
    val width: Int?,
    val height: Int?,
)


