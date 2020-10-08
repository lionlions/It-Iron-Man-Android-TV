package com.cindy.myfirstandroidtvapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.cindy.myfirstandroidtvapp.CustomView.CustomHeaderItem
import com.cindy.myfirstandroidtvapp.Model.Data
import com.cindy.myfirstandroidtvapp.Model.MovieList
import com.google.gson.Gson

class MainFragment: BrowseSupportFragment() {

    private val TAG: String = javaClass.simpleName
    private var mMovieList: MovieList? = null
    private var mMovieListData: List<Data>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieList()
        init()
        mainFragmentRegistry.registerFragment(PageRow::class.java, PageRowFragmentFactory(activity))

    }

    fun getMovieList(){
        val jsonFileString: String = resources.openRawResource(R.raw.movielist).bufferedReader().use { it.readText() }
        if(BuildConfig.DEBUG) Log.d(TAG, "jsonFileString: $jsonFileString")
        mMovieList = Gson().fromJson(jsonFileString, MovieList::class.java)
        if(BuildConfig.DEBUG) Log.i(TAG, "mMovieList: $mMovieList")
    }

    fun init(){

        val mainAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mainAdapter

        if(mMovieList!=null){
            mMovieListData = mMovieList!!.data
            if(mMovieListData!=null && mMovieListData!!.isNotEmpty()){
                for((categoryIndex, category) in mMovieListData!!.withIndex()){
                    val categoryName: String? = category.category_name
                    if(BuildConfig.DEBUG) Log.w(TAG, "categoryName: $categoryName")
                    val header: CustomHeaderItem =
                        CustomHeaderItem(
                            categoryIndex.toLong(),
                            categoryName,
                            category
                        )
                    val pageRow: PageRow = PageRow(header)
                    mainAdapter.add(pageRow)
                }
            }
        }

        if(context!=null){
            //左側 HeaderSupportFragment 的背景
            brandColor = ContextCompat.getColor(context!!, R.color.header_background)
            //右側右上方 icon
            badgeDrawable = ContextCompat.getDrawable(context!!, R.drawable.vscinemas_logo)
        }
    }
}