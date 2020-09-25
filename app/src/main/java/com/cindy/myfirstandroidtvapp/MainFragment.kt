package com.cindy.myfirstandroidtvapp

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRowPresenter

class MainFragment: BrowseSupportFragment() {

    private val TAG: String = javaClass.simpleName
    private var mRowsAdapter: ArrayObjectAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter

        if(context!=null){
            brandColor = ContextCompat.getColor(context!!, android.R.color.holo_blue_light)
            badgeDrawable = ContextCompat.getDrawable(context!!, android.R.drawable.ic_media_play)
        }

    }

}