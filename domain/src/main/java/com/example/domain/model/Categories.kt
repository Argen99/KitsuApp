package com.example.domain.model

data class CategoriesResponse(
    val data: List<CategoriesData>,
    val meta: CategoriesMeta,
    val links: CategoriesLinksXXXX,
)

data class CategoriesLinksXXXX(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class CategoriesMeta(
    val count: Int?
)

data class CategoriesData(
    val id: String?,
    val type: String?,
    val links: CategoriesLinks?,
    val attributes: CategoriesAttributes,
    val relationships: CategoriesRelationships?
)

data class CategoriesRelationships(
    val parent: CategoriesLinksXX?,
    val anime: CategoriesLinksXX?,
    val drama: CategoriesLinksXX?,
    val manga: CategoriesLinksXX?
)

data class CategoriesLinksXX(
    val links: CategoriesLinksXXX?
)

data class CategoriesAttributes(
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

data class CategoriesLinksXXX(
    val self: String?,
    val related: String?,
)

data class CategoriesLinks (
    val self: String?
)
