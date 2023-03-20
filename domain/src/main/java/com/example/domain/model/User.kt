package com.example.domain.model

data class UsersResponse(
    val data: List<User>,
    val meta: UserCountMeta,
    val links: UsLinks?,
)

data class UserResponse(
    val data: User
)

data class UserCountMeta(
    val count: Int
)

data class UsLinks(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class User(
    val id: String?,
    val type: String?,
    val links: UserLinks?,
    val attributes: UserAttributes?,
    val relationships: UserRelationships?
)

data class UserAttributes(
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
    val avatar: UserAvatar?,
    val coverImage: UserCoverImage?,
    val status: String?,
    val subscribedToNewsletter: Boolean?,
    val permissions: String?,
    val sfwFilterPreference: String?,
)

data class UserRelationships(
    val waifu: ULinks?,
    val pinnedPost: ULinks?,
    val followers: ULinks?,
    val following: ULinks?,
    val blocks: ULinks?,
    val linkedAccounts: ULinks?,
    val profileLinks: ULinks?,
    val userRoles: ULinks?,
    val libraryEntries: ULinks?,
    val favorites: ULinks?,
    val reviews: ULinks?,
    val stats: ULinks?,
    val notificationSettings: ULinks?,
    val oneSignalPlayers: ULinks?,
    val categoryFavorites: ULinks?,
    val quotes: ULinks?,
)

data class ULinks(
    val links: UserLinks?
)

data class UserLinks(
    val self: String?,
    val related: String?,
)

data class UserCoverImage(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val original: String?,
    val meta: UserMeta?,
)

data class UserAvatar(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: UserMeta?
)

data class UserMeta(
    val dimensions: UserDimensions?
)

data class UserDimensions(
    val tiny: UDimensions?,
    val large: UDimensions?,
    val small: UDimensions?,
    val medium: UDimensions?
)

data class UDimensions(
    val width: Int?,
    val height: Int?
)


