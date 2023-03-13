package com.example.data.remote.model.mappers

import com.example.data.remote.model.*
import com.example.domain.model.*

fun AnimeResponse.toDto() = AnimeResponseDto(
    data = data.map {
        it.toDto()
    },
    count = count,
    links = links?.toDto()
)

fun Links.toDto() = LinksDto(
    first = first,
    next = next,
    last = last
)

fun Data.toDto() = DataDto(
    id = id,
    type = type,
    links = links?.toDto(),
    attributes = attributes?.toDto(),
    relationships = relationships?.toDto()
)

fun Relationships.toDto() = RelationshipsDto(
    genres = genres?.toDto(),
    categories = categories?.toDto(),
    castings = castings?.toDto(),
    installments = installments?.toDto(),
    mappings = mappings?.toDto(),
    reviews = reviews?.toDto(),
    mediaRelationships = mediaRelationships?.toDto(),
    characters = characters?.toDto(),
    staff = staff?.toDto(),
    productions = productions?.toDto(),
    quotes = quotes?.toDto(),
    episodes = episodes?.toDto(),
    streamingLinks = streamingLinks?.toDto(),
    animeProductions = animeProductions?.toDto(),
    animeCharacters = animeCharacters?.toDto(),
    animeStaff = animeStaff?.toDto(),
)

fun LinksX.toDto() = LinksXDto(
    links = links?.toDto()
)

fun Links2.toDto() = Links2Dto(
    self = self,
    related = related
)

fun Attributes.toDto() = AttributesDto(
    createdAt = createdAt,
    updatedAt = updatedAt,
    slug = slug,
    synopsis = synopsis,
    description = description,
    coverImageTopOffset = coverImageTopOffset,
    titles = titles?.toDto(),
    canonicalTitle = canonicalTitle,
    abbreviatedTitles = abbreviatedTitles,
    averageRating = averageRating,
    ratingFrequencies = ratingFrequencies?.toDto(),
    userCount = userCount,
    favoritesCount = favoritesCount,
    startDate = startDate,
    endDate = endDate,
    nextRelease = nextRelease,
    popularityRank = popularityRank,
    ratingRank = ratingRank,
    ageRating = ageRating,
    ageRatingGuide = ageRatingGuide,
    subtype = subtype,
    status = status,
    tba = tba,
    posterImage = posterImage?.toDto(),
    coverImage = coverImage?.toDto(),
    episodeCount = episodeCount,
    episodeLength = episodeLength,
    totalLength = totalLength,
    youtubeVideoId = youtubeVideoId,
    showType = showType,
    nsfw = nsfw,
)

fun PosterImage.toDto() = PosterImageDto(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toDto()
)

fun Meta.toDto() = MetaDto(
    dimensions = dimensions?.toDto()
)

fun Dimensions.toDto() = DimensionsDto(
    tiny = tiny?.toDto(),
    large = large?.toDto(),
    small = small?.toDto(),
    medium = medium?.toDto(),
)

fun Size.toDto() = SizeDto(
    width = width,
    height = height
)

fun RatingFrequencies.toDto() = RatingFrequenciesDto(
    `2` = `2`,
    `3` = `3`,
    `4` = `4`,
    `5` = `5`,
    `6` = `6`,
    `7` = `7`,
    `8` = `8`,
    `9` = `9`,
    `10` = `10`,
    `11` = `11`,
    `12` = `12`,
    `13` = `13`,
    `14` = `14`,
    `15` = `15`,
    `16` = `16`,
    `17` = `17`,
    `18` = `18`,
    `19` = `19`,
    `20` = `20`,
)

fun Titles.toDto() = TitlesDto(
    en = en,
    en_jp = en_jp,
    ja_jp = ja_jp
)

// To Model
fun AnimeResponseDto.toModel() = AnimeResponse(
    data = data.map {
        it.toModel()
    },
    count = count,
    links = links?.toModel()
)

fun LinksDto.toModel() = Links(
    first = first,
    next = next,
    last = last
)

fun DataDto.toModel() = Data(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes?.toModel(),
    relationships = relationships?.toModel()
)

fun RelationshipsDto.toModel() = Relationships(
    genres = genres?.toModel(),
    categories = categories?.toModel(),
    castings = castings?.toModel(),
    installments = installments?.toModel(),
    mappings = mappings?.toModel(),
    reviews = reviews?.toModel(),
    mediaRelationships = mediaRelationships?.toModel(),
    characters = characters?.toModel(),
    staff = staff?.toModel(),
    productions = productions?.toModel(),
    quotes = quotes?.toModel(),
    episodes = episodes?.toModel(),
    streamingLinks = streamingLinks?.toModel(),
    animeProductions = animeProductions?.toModel(),
    animeCharacters = animeCharacters?.toModel(),
    animeStaff = animeStaff?.toModel(),
)

fun LinksXDto.toModel() = LinksX(
    links = links?.toModel()
)

fun Links2Dto.toModel() = Links2(
    self = self,
    related = related
)

fun AttributesDto.toModel() = Attributes(
    createdAt = createdAt,
    updatedAt = updatedAt,
    slug = slug,
    synopsis = synopsis,
    description = description,
    coverImageTopOffset = coverImageTopOffset,
    titles = titles?.toModel(),
    canonicalTitle = canonicalTitle,
    abbreviatedTitles = abbreviatedTitles,
    averageRating = averageRating,
    ratingFrequencies = ratingFrequencies?.toModel(),
    userCount = userCount,
    favoritesCount = favoritesCount,
    startDate = startDate,
    endDate = endDate,
    nextRelease = nextRelease,
    popularityRank = popularityRank,
    ratingRank = ratingRank,
    ageRating = ageRating,
    ageRatingGuide = ageRatingGuide,
    subtype = subtype,
    status = status,
    tba = tba,
    posterImage = posterImage?.toModel(),
    coverImage = coverImage?.toModel(),
    episodeCount = episodeCount,
    episodeLength = episodeLength,
    totalLength = totalLength,
    youtubeVideoId = youtubeVideoId,
    showType = showType,
    nsfw = nsfw,
)

fun PosterImageDto.toModel() = PosterImage(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toModel()
)

fun MetaDto.toModel() = Meta(
    dimensions = dimensions?.toModel()
)

fun DimensionsDto.toModel() = Dimensions(
    tiny = tiny?.toModel(),
    large = large?.toModel(),
    small = small?.toModel(),
    medium = medium?.toModel(),
)

fun SizeDto.toModel() = Size(
    width = width,
    height = height
)

fun RatingFrequenciesDto.toModel() = RatingFrequencies(
    `2` = `2`,
    `3` = `3`,
    `4` = `4`,
    `5` = `5`,
    `6` = `6`,
    `7` = `7`,
    `8` = `8`,
    `9` = `9`,
    `10` = `10`,
    `11` = `11`,
    `12` = `12`,
    `13` = `13`,
    `14` = `14`,
    `15` = `15`,
    `16` = `16`,
    `17` = `17`,
    `18` = `18`,
    `19` = `19`,
    `20` = `20`,
)

fun TitlesDto.toModel() = Titles(
    en = en,
    en_jp = en_jp,
    ja_jp = ja_jp
)