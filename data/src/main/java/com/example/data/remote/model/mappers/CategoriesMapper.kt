package com.example.data.remote.model.mappers

import com.example.data.remote.model.*
import com.example.domain.model.*

fun CategoriesResponseDto.toModel() = CategoriesResponse(
    data = data.map { it.toModel() },
    meta = meta.toModel(),
    links = links.toModel()
)

fun CategoriesLinksXXXXDto.toModel() = CategoriesLinksXXXX(
    first = first,
    next = next,
    last = last
)

fun CategoriesMetaDto.toModel() = CategoriesMeta(
    count = count
)

fun CategoriesDataDto.toModel() = CategoriesData(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes.toModel(),
    relationships = relationships?.toModel()
)

fun CategoriesRelationshipsDto.toModel() = CategoriesRelationships(
    parent = parent?.toModel(),
    anime = anime?.toModel(),
    drama = drama?.toModel(),
    manga = manga?.toModel()
)

fun CategoriesLinksXXDto.toModel() = CategoriesLinksXX(
    links = links?.toModel()
)

fun CategoriesAttributesDto.toModel() = CategoriesAttributes(
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = title,
    description = description,
    totalMediaCount = totalMediaCount,
    slug = slug,
    nsfw = nsfw,
    childCount = childCount,
    isChecked = isChecked
)

fun CategoriesLinksXXXDto.toModel() = CategoriesLinksXXX(
    self = self,
    related = related
)

fun CategoriesLinksDto.toModel() = CategoriesLinks(
    self = self
)


// TO DTO ***

fun CategoriesResponse.toDto() = CategoriesResponseDto(
    data = data.map { it.toDto() },
    meta = meta.toDto(),
    links = links.toDto()
)

fun CategoriesLinksXXXX.toDto() = CategoriesLinksXXXXDto(
    first = first,
    next = next,
    last = last
)

fun CategoriesMeta.toDto() = CategoriesMetaDto(
    count = count
)

fun CategoriesData.toDto() = CategoriesDataDto(
    id = id,
    type = type,
    links = links?.toDto(),
    attributes = attributes.toDto(),
    relationships = relationships?.toDto()
)

fun CategoriesRelationships.toDto() = CategoriesRelationshipsDto(
    parent = parent?.toDto(),
    anime = anime?.toDto(),
    drama = drama?.toDto(),
    manga = manga?.toDto()
)

fun CategoriesLinksXX.toDto() = CategoriesLinksXXDto(
    links = links?.toDto()
)

fun CategoriesAttributes.toDto() = CategoriesAttributesDto(
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = title,
    description = description,
    totalMediaCount = totalMediaCount,
    slug = slug,
    nsfw = nsfw,
    childCount = childCount,
    isChecked = isChecked
)

fun CategoriesLinksXXX.toDto() = CategoriesLinksXXXDto(
    self = self,
    related = related
)

fun CategoriesLinks.toDto() = CategoriesLinksDto(
    self = self
)