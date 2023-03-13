package com.example.kitsuapp.model

data class UserResponseUI(
    val data: List<UserUI>,
    val meta: UserCountMetaUI,
    val links: UsLinksUI?,
)

data class UserCountMetaUI(
    val count: Int
)

data class UsLinksUI(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class UserUI(
    val id: String?,
    val type: String?,
    val links: UserLinksUI?,
    val attributes: UserAttributesUI?,
    val relationships: UserRelationshipsUI?
)

data class UserAttributesUI(
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
    val avatar: UserAvatarUI?,
    val coverImage: UserCoverImageUI?,
    val status: String?,
    val subscribedToNewsletter: Boolean?,
    val permissions: String?,
    val sfwFilterPreference: String?,
)

data class UserRelationshipsUI(
    val waifu: ULinksUI?,
    val pinnedPost: ULinksUI?,
    val followers: ULinksUI?,
    val following: ULinksUI?,
    val blocks: ULinksUI?,
    val linkedAccounts: ULinksUI?,
    val profileLinks: ULinksUI?,
    val userRoles: ULinksUI?,
    val libraryEntries: ULinksUI?,
    val favorites: ULinksUI?,
    val reviews: ULinksUI?,
    val stats: ULinksUI?,
    val notificationSettings: ULinksUI?,
    val oneSignalPlayers: ULinksUI?,
    val categoryFavorites: ULinksUI?,
    val quotes: ULinksUI?,
)

data class ULinksUI(
    val links: UserLinksUI?
)

data class UserLinksUI(
    val self: String?,
    val related: String?,
)

data class UserCoverImageUI(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val original: String?,
    val meta: UserMetaUI?,
)

data class UserAvatarUI(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: UserMetaUI?
)

data class UserMetaUI(
    val dimensions: UserDimensionsUI?
)

data class UserDimensionsUI(
    val tiny: UDimensionsUI?,
    val large: UDimensionsUI?,
    val small: UDimensionsUI?,
    val medium: UDimensionsUI?
)

data class UDimensionsUI(
    val width: Int?,
    val height: Int?
)


