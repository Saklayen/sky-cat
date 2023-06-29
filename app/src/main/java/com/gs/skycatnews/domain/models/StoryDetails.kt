package com.gs.skycatnews.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryDetails(
    @Json(name = "contents")
    val contents: List<Content>,
    @Json(name = "creationDate")
    val creationDate: String,
    @Json(name = "headline")
    val headline: String,
    @Json(name = "heroImage")
    val heroImage: HeroImage,
    @Json(name = "id")
    val id: String,
    @Json(name = "modifiedDate")
    val modifiedDate: String
)

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "accessibilityText")
    val accessibilityText: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class HeroImage(
    @Json(name = "accessibilityText")
    val accessibilityText: String,
    @Json(name = "imageUrl")
    val imageUrl: String
)