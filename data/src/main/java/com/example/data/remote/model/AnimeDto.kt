package com.example.data.remote.model

data class AnimeResponseDto(
    val data: List<DataDto>,
    val count: Int?,
    val links: LinksDto?,
)

data class LinksDto(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class DataDto(
    val id: String?,
    val type: String?,
    val links: Links2Dto?,
    val attributes: AttributesDto?,
    val relationships: RelationshipsDto?,
)

data class RelationshipsDto(
    val genres: LinksXDto?,
    val categories: LinksXDto?,
    val castings: LinksXDto?,
    val installments: LinksXDto?,
    val mappings: LinksXDto?,
    val reviews: LinksXDto?,
    val mediaRelationships: LinksXDto?,
    val characters: LinksXDto?,
    val staff: LinksXDto?,
    val productions: LinksXDto?,
    val quotes: LinksXDto?,
    val episodes: LinksXDto?,
    val streamingLinks: LinksXDto?,
    val animeProductions: LinksXDto?,
    val animeCharacters: LinksXDto?,
    val animeStaff: LinksXDto?,
)

data class LinksXDto(
    val links: Links2Dto?,
)


data class Links2Dto(
    val self: String?,
    val related: String?
)

data class AttributesDto(
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?,
    val synopsis: String?,
    val description: String?,
    val coverImageTopOffset: Int?,
    val titles: TitlesDto?,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>?,
    val averageRating: String?,
    val ratingFrequencies: RatingFrequenciesDto?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val nextRelease: Any?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val subtype: String?,
    val status: String?,
    val tba: String?,
    val posterImage: PosterImageDto?,
    val coverImage: PosterImageDto?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val totalLength: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
    val nsfw: Boolean?,
)

data class PosterImageDto(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: MetaDto?,
)

data class MetaDto(
    val dimensions: DimensionsDto?
)

data class DimensionsDto(
    val tiny: SizeDto?,
    val large: SizeDto?,
    val small: SizeDto?,
    val medium: SizeDto?,
)

data class SizeDto(
    val width: Int?,
    val height: Int?
)

data class RatingFrequenciesDto(
    val `2`: String?,
    val `3`: String?,
    val `4`: String?,
    val `5`: String?,
    val `6`: String?,
    val `7`: String?,
    val `8`: String?,
    val `9`: String?,
    val `10`: String?,
    val `11`: String?,
    val `12`: String?,
    val `13`: String?,
    val `14`: String?,
    val `15`: String?,
    val `16`: String?,
    val `17`: String?,
    val `18`: String?,
    val `19`: String?,
    val `20`: String?
)

data class TitlesDto(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
)


