package com.cindy.myfirstandroidtvapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.cindy.myfirstandroidtvapp.model.Data
import com.cindy.myfirstandroidtvapp.model.Item
import com.cindy.myfirstandroidtvapp.model.MovieList
import com.google.gson.Gson

class MainFragment: BrowseSupportFragment() {

    private val TAG: String = javaClass.simpleName
    private var mRowsAdapter: ArrayObjectAdapter? = null
    private var mMovieList: MovieList? = null
    private var mMovieListData: List<Data>? = null

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
        val cardPresenter: CustomCardPresenter = CustomCardPresenter()

        if(mMovieList!=null){
            mMovieListData = mMovieList!!.data
            if(mMovieListData!=null && mMovieListData!!.size>0){
                for((index, categories) in mMovieListData!!.withIndex()){
                    val listRowAdapter: ArrayObjectAdapter = ArrayObjectAdapter(cardPresenter)
                    val categoryName: String? = categories.category_name
                    val items: List<Item>? = categories.items
                    if(items!=null && items.size>0){
                        for(item in items){
                            listRowAdapter.add(item)
                        }
                        val header: HeaderItem = HeaderItem(index.toLong(), categoryName)
                        if(mRowsAdapter!=null) mRowsAdapter!!.add(ListRow(header, listRowAdapter))
                    }
                }
            }
        }
        adapter = mRowsAdapter
        if(context!=null){
            //左側 HeaderSupportFragment 的背景
            brandColor = ContextCompat.getColor(context!!, R.color.header_background)
            //右側右上方 icon
            badgeDrawable = ContextCompat.getDrawable(context!!, R.drawable.vscinemas_logo)
        }
    }

}