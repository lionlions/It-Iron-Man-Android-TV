package com.cindy.myfirstandroidtvapp.CustomView

import androidx.leanback.widget.HeaderItem

class CustomHeaderItem(id: Long,
                       name: String?,
                       var item: Any? = null): HeaderItem(id, name)