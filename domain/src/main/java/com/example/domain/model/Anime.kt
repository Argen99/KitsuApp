package com.example.domain.model

data class AnimeResponse(
    val data: List<Data>,
    val count: Int?,
    val links: Links?,
)

data class Links(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class Data(
    val id: String?,
    val type: String?,
    val links: Links2?,
    val attributes: Attributes?,
    val relationships: Relationships?,
)

data class Relationships(
    val genres: LinksX?,
    val categories: LinksX?,
    val castings: LinksX?,
    val installments: LinksX?,
    val mappings: LinksX?,
    val reviews: LinksX?,
    val mediaRelationships: LinksX?,
    val characters: LinksX?,
    val staff: LinksX?,
    val productions: LinksX?,
    val quotes: LinksX?,
    val episodes: LinksX?,
    val streamingLinks: LinksX?,
    val animeProductions: LinksX?,
    val animeCharacters: LinksX?,
    val animeStaff: LinksX?,
)

data class LinksX(
    val links: Links2?,
)


data class Links2(
    val self: String?,
    val related: String?
)

data class Attributes(
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?,
    val synopsis: String?,
    val description: String?,
    val coverImageTopOffset: Int?,
    val titles: Titles?,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>?,
    val averageRating: String?,
    val ratingFrequencies: RatingFrequencies?,
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
    val posterImage: PosterImage?,
    val coverImage: PosterImage?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val totalLength: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
    val nsfw: Boolean?,
)

data class PosterImage(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: Meta?,
)

data class Meta(
    val dimensions: Dimensions?
)

data class Dimensions(
    val tiny: Size?,
    val large: Size?,
    val small: Size?,
    val medium: Size?,
)

data class Size(
    val width: Int?,
    val height: Int?
)

data class RatingFrequencies(
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

data class Titles(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
)