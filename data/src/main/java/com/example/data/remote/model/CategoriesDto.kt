package com.example.data.remote.model

data class CategoriesResponseDto(
    val data: List<CategoriesDataDto>,
    val meta: CategoriesMetaDto,
    val links: CategoriesLinksXXXXDto,
)

data class CategoriesLinksXXXXDto(
    val first: String?,
    val next: String?,
    val last: String?
)

data class CategoriesMetaDto(
    val count: Int?
)

data class CategoriesDataDto(
    val id: String?,
    val type: String?,
    val links: CategoriesLinksDto?,
    val attributes: CategoriesAttributesDto,
    val relationships: CategoriesRelationshipsDto?
)

data class CategoriesRelationshipsDto(
    val parent: CategoriesLinksXXDto?,
    val anime: CategoriesLinksXXDto?,
    val drama: CategoriesLinksXXDto?,
    val manga: CategoriesLinksXXDto?
)

data class CategoriesLinksXXDto(
    val links: CategoriesLinksXXXDto?
)

data class CategoriesAttributesDto(
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

data class CategoriesLinksXXXDto(
    val self: String?,
    val related: String?,
)

data class CategoriesLinksDto(
    val self: String?
)