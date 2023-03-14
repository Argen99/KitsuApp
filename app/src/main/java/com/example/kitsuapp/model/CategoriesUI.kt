package com.example.kitsuapp.model

data class CategoriesResponseUI(
    val data: List<CategoriesDataUI>,
    val meta: CategoriesMetaUI,
    val links: CategoriesLinksXXXXUI,
)

data class CategoriesLinksXXXXUI(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class CategoriesMetaUI(
    val count: Int?
)

data class CategoriesDataUI(
    val id: String?,
    val type: String?,
    val links: CategoriesLinksUI?,
    val attributes: CategoriesAttributesUI,
    val relationships: CategoriesRelationshipsUI?
)

data class CategoriesRelationshipsUI(
    val parent: CategoriesLinksXXUI?,
    val anime: CategoriesLinksXXUI?,
    val drama: CategoriesLinksXXUI?,
    val manga: CategoriesLinksXXUI?
)

data class CategoriesLinksXXUI(
    val links: CategoriesLinksXXXUI?
)

data class CategoriesAttributesUI(
    val createdAt: String?,
    val updatedAt: String?,
    val title: String,
    val description: String?,
    val totalMediaCount: Int?,
    val slug: String?,
    val nsfw: Boolean?,
    val childCount: Int?,
    var isChecked: Boolean = false
)

data class CategoriesLinksXXXUI(
    val self: String?,
    val related: String?,
)

data class CategoriesLinksUI (
    val self: String?
)
