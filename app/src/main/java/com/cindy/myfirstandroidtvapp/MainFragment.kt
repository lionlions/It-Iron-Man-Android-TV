package com.cindy.myfirstandroidtvapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRowPresenter
import com.cindy.myfirstandroidtvapp.model.MovieList
import com.google.gson.Gson

class MainFragment: BrowseSupportFragment() {

    private val TAG: String = javaClass.simpleName
    private var mRowsAdapter: ArrayObjectAdapter? = null
    private var mMovieList: MovieList? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieList()
        init()
    }

    fun getMovieList(){
        val jsonFileString: String = resources.openRawResource(R.raw.day12).bufferedReader().use { it.readText() }
        if(BuildConfig.DEBUG) Log.d(TAG, "jsonFileString: $jsonFileString")
        mMovieList = Gson().fromJson(jsonFileString, MovieList::class.java)
        if(BuildConfig.DEBUG) Log.i(TAG, "mMovieList: $mMovieList")
    }

    fun init(){
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        if(context!=null){
            //左側 HeaderSupportFragment 的背景
            brandColor = ContextCompat.getColor(context!!, android.R.color.holo_blue_light)
            //右側右上方 icon
            badgeDrawable = ContextCompat.getDrawable(context!!, android.R.drawable.ic_media_play)
        }
    }

}