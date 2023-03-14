package com.example.kitsuapp.model.mappers

import com.example.domain.model.*
import com.example.kitsuapp.model.*

fun CategoriesResponse.toUI() = CategoriesResponseUI(
    data = data.map { it.toUI() },
    meta = meta.toUI(),
    links = links.toUI()
)

fun CategoriesLinksXXXX.toUI() = CategoriesLinksXXXXUI(
    first = first,
    next = next,
    last = last
)

fun CategoriesMeta.toUI() = CategoriesMetaUI(
    count = count
)

fun CategoriesData.toUI() = CategoriesDataUI(
    id = id,
    type = type,
    links = links?.toUI(),
    attributes = attributes.toUI(),
    relationships = relationships?.toUI()
)

fun CategoriesRelationships.toUI() = CategoriesRelationshipsUI(
    parent = parent?.toUI(),
    anime = anime?.toUI(),
    drama = drama?.toUI(),
    manga = manga?.toUI()
)

fun CategoriesLinksXX.toUI() = CategoriesLinksXXUI(
    links =links?.toUI()
)

fun CategoriesAttributes.toUI() = CategoriesAttributesUI(
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

fun CategoriesLinksXXX.toUI() = CategoriesLinksXXXUI(
    self = self,
    related = related
)

fun CategoriesLinks.toUI() = CategoriesLinksUI(
    self = self
)