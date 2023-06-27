package com.gs.skycatnews.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsFeed(
    @Json(name = "data")
    val feeds: List<Feed>,
    @Json(name = "title")
    val title: String
)

@JsonClass(generateAdapter = true)
data class Feed(
    @Json(name = "creationDate")
    val creationDate: String?,
    @Json(name = "headline")
    val headline: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "modifiedDate")
    val modifiedDate: String?,
    @Json(name = "teaserImage")
    val teaserImage: TeaserImage?,
    @Json(name = "teaserText")
    val teaserText: String?,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "weblinkUrl")
    val weblinkUrl: String?
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "url")
    val url: Url
)

@JsonClass(generateAdapter = true)
data class TeaserImage(
    @Json(name = "accessibilityText")
    val accessibilityText: String,
    @Json(name = "_links")
    val links: Links
)

@JsonClass(generateAdapter = true)
data class Url(
    @Json(name = "href")
    val href: String,
    @Json(name = "templated")
    val templated: Boolean,
    @Json(name = "type")
    val type: String
)