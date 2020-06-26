package com.tmobile.dynamicui.data.model

import com.google.gson.annotations.SerializedName


data class HomeResponse (
    @SerializedName("page") val page : Page
)

data class Page(
    @SerializedName("cards") val cards : List<Cards>
)

data class Cards(
    @SerializedName("card_type") val card_type: String,
    @SerializedName("card") val card: Card
)

data class Card(
    @SerializedName("value") var value: String?,
    @SerializedName("attributes") var attributes: Attributes?,
    @SerializedName("image") var image: Image?,
    @SerializedName("title") var title: Title?,
    @SerializedName("description") var description: Description?
)


data class Attributes(
    @SerializedName("text_color") var text_color: String,
    @SerializedName("font") var font: Font
)

data class Font(
    @SerializedName("size") var size: Int
)

data class Title(
    @SerializedName("value") var value: String,
    @SerializedName("attributes") var attributes: Attributes
)

data class Description(
    @SerializedName("value") var value: String,
    @SerializedName("attributes") var attributes: Attributes
)

data class Image(
    @SerializedName("url") var url: String,
    @SerializedName("size") var size: Size
)

data class Size(
    @SerializedName("width") var width: Int,
    @SerializedName("height") var height: Int
)