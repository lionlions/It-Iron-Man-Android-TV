package com.cindy.myfirstandroidtvapp.Model

data class MovieList(
    var data: List<Data>? = null
)

data class Data(
    var category_name: String? = null,
    var sub_categories: List<SubCategory>? = null
)

data class SubCategory(
    var sub_category_name: String? = null,
    var items: List<Item>? = null
)

data class Item(
    var name: String? = null,
    var imageUrl: String? = null
)