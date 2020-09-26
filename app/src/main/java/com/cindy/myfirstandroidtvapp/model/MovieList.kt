package com.cindy.myfirstandroidtvapp.model

data class MovieList(
    var data: List<Data>? = null
)

data class Data(
    var category_name: String? = null,
    var items: List<Item>? = null
)

data class Item(
    var imageUrl: String? = null,
    var name: String? = null
)