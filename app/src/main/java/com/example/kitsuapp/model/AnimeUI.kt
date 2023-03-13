package com.example.kitsuapp.model

data class AnimeResponseUI(
    val data: List<DataUI>,
    val count: Int?,
    val links: LinksUI?,
)

data class LinksUI(
    val first: String?,
    val next: String?,
    val last: String?,
)

data class DataUI(
    val id: String?,
    val type: String?,
    val links: Links2UI?,
    val attributes: AttributesUI?,
    val relationships: RelationshipsUI?,
)

data class RelationshipsUI(
    val genres: LinksXUI?,
    val categories: LinksXUI?,
    val castings: LinksXUI?,
    val installments: LinksXUI?,
    val mappings: LinksXUI?,
    val reviews: LinksXUI?,
    val mediaRelationships: LinksXUI?,
    val characters: LinksXUI?,
    val staff: LinksXUI?,
    val productions: LinksXUI?,
    val quotes: LinksXUI?,
    val episodes: LinksXUI?,
    val streamingLinks: LinksXUI?,
    val animeProductions: LinksXUI?,
    val animeCharacters: LinksXUI?,
    val animeStaff: LinksXUI?,
)

data class LinksXUI(
    val links: Links2UI?,
)


data class Links2UI(
    val self: String?,
    val related: String?
)

data class AttributesUI(
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?,
    val synopsis: String?,
    val description: String?,
    val coverImageTopOffset: Int?,
    val titles: TitlesUI?,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>?,
    val averageRating: String?,
    val ratingFrequencies: RatingFrequenciesUI?,
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
    val posterImage: PosterImageUI?,
    val coverImage: PosterImageUI?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val totalLength: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
    val nsfw: Boolean?,
)

data class PosterImageUI(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?,
    val meta: MetaUI?,
)

data class MetaUI(
    val dimensions: DimensionsUI?
)

data class DimensionsUI(
    val tiny: SizeUI?,
    val large: SizeUI?,
    val small: SizeUI?,
    val medium: SizeUI?,
)

data class SizeUI(
    val width: Int?,
    val height: Int?
)

data class RatingFrequenciesUI(
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

data class TitlesUI(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
)