package com.example.kitsuapp.model.mappers

import com.example.domain.model.*
import com.example.kitsuapp.model.*

fun AnimeResponse.toUI() = AnimeResponseUI(
    data = data.map {
        it.toUI()
    },
    count = count,
    links = links?.toUI()
)

fun Links.toUI() = LinksUI(
    first = first,
    next = next,
    last = last
)

fun Data.toUI() = DataUI(
    id = id,
    type = type,
    links = links?.toUI(),
    attributes = attributes?.toUI(),
    relationships = relationships?.toUI()
)

fun Relationships.toUI() = RelationshipsUI(
    genres = genres?.toUI(),
    categories = categories?.toUI(),
    castings = castings?.toUI(),
    installments = installments?.toUI(),
    mappings = mappings?.toUI(),
    reviews = reviews?.toUI(),
    mediaRelationships = mediaRelationships?.toUI(),
    characters = characters?.toUI(),
    staff = staff?.toUI(),
    productions = productions?.toUI(),
    quotes = quotes?.toUI(),
    episodes = episodes?.toUI(),
    streamingLinks = streamingLinks?.toUI(),
    animeProductions = animeProductions?.toUI(),
    animeCharacters = animeCharacters?.toUI(),
    animeStaff = animeStaff?.toUI(),
)

fun LinksX.toUI() = LinksXUI(
    links = links?.toUI()
)

fun Links2.toUI() = Links2UI(
    self = self,
    related = related
)

fun Attributes.toUI() = AttributesUI(
    createdAt = createdAt,
    updatedAt = updatedAt,
    slug = slug,
    synopsis = synopsis,
    description = description,
    coverImageTopOffset = coverImageTopOffset,
    titles = titles?.toUI(),
    canonicalTitle = canonicalTitle,
    abbreviatedTitles = abbreviatedTitles,
    averageRating = averageRating,
    ratingFrequencies = ratingFrequencies?.toUI(),
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
    posterImage = posterImage?.toUI(),
    coverImage = coverImage?.toUI(),
    episodeCount = episodeCount,
    episodeLength = episodeLength,
    totalLength = totalLength,
    youtubeVideoId = youtubeVideoId,
    showType = showType,
    nsfw = nsfw,
)

fun PosterImage.toUI() = PosterImageUI(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toUI()
)

fun Meta.toUI() = MetaUI(
    dimensions = dimensions?.toUI()
)

fun Dimensions.toUI() = DimensionsUI(
    tiny = tiny?.toUI(),
    large = large?.toUI(),
    small = small?.toUI(),
    medium = medium?.toUI(),
)

fun Size.toUI() = SizeUI(
    width = width,
    height = height
)

fun RatingFrequencies.toUI() = RatingFrequenciesUI(
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

fun Titles.toUI() = TitlesUI(
    en = en,
    en_jp = en_jp,
    ja_jp = ja_jp
)

// To Model
fun AnimeResponseUI.toModel() = AnimeResponse(
    data = data.map {
        it.toModel()
    },
    count = count,
    links = links?.toModel()
)

fun LinksUI.toModel() = Links(
    first = first,
    next = next,
    last = last
)

fun DataUI.toModel() = Data(
    id = id,
    type = type,
    links = links?.toModel(),
    attributes = attributes?.toModel(),
    relationships = relationships?.toModel()
)

fun RelationshipsUI.toModel() = Relationships(
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

fun LinksXUI.toModel() = LinksX(
    links = links?.toModel()
)

fun Links2UI.toModel() = Links2(
    self = self,
    related = related
)

fun AttributesUI.toModel() = Attributes(
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

fun PosterImageUI.toModel() = PosterImage(
    tiny = tiny,
    large = large,
    small = small,
    medium = medium,
    original = original,
    meta = meta?.toModel()
)

fun MetaUI.toModel() = Meta(
    dimensions = dimensions?.toModel()
)

fun DimensionsUI.toModel() = Dimensions(
    tiny = tiny?.toModel(),
    large = large?.toModel(),
    small = small?.toModel(),
    medium = medium?.toModel(),
)

fun SizeUI.toModel() = Size(
    width = width,
    height = height
)

fun RatingFrequenciesUI.toModel() = RatingFrequencies(
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

fun TitlesUI.toModel() = Titles(
    en = en,
    en_jp = en_jp,
    ja_jp = ja_jp
)