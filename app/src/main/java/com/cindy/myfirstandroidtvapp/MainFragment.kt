package com.cindy.myfirstandroidtvapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.cindy.myfirstandroidtvapp.model.Data
import com.cindy.myfirstandroidtvapp.model.Item
import com.cindy.myfirstandroidtvapp.model.MovieList
import com.cindy.myfirstandroidtvapp.model.SubCategory
import com.google.gson.Gson

class MainFragment: BrowseSupportFragment() {

    private val TAG: String = javaClass.simpleName
    private var mMovieList: MovieList? = null
    private var mMovieListData: List<Data>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieList()
        init()

    }

    fun getMovieList(){
        val jsonFileString: String = resources.openRawResource(R.raw.movielist).bufferedReader().use { it.readText() }
        if(BuildConfig.DEBUG) Log.d(TAG, "jsonFileString: $jsonFileString")
        mMovieList = Gson().fromJson(jsonFileString, MovieList::class.java)
        if(BuildConfig.DEBUG) Log.i(TAG, "mMovieList: $mMovieList")
    }

    fun init(){

        val mainAdapter: ArrayObjectAdapter = ArrayObjectAdapter(RowPresenter())
        mainAdapter.presenterSelector = object:PresenterSelector(){
            override fun getPresenter(item: Any?): Presenter {
                Log.i(TAG, "item: $item")
                return ListRowPresenter()
            }

            override fun getPresenters(): Array<Presenter> {
                return super.getPresenters()
            }
        }

        if(mMovieList!=null){
            mMovieListData = mMovieList!!.data
            if(mMovieListData!=null && mMovieListData!!.isNotEmpty()){
                for((categoryIndex, category) in mMovieListData!!.withIndex()){
                    val rowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter())
                    val categoryName: String? = category.category_name
                    if(BuildConfig.DEBUG) Log.w(TAG, "categoryName: $categoryName")
                    val subCategoryList: List<SubCategory>? = category.sub_categories
                    if(subCategoryList!=null && subCategoryList.isNotEmpty()){
                        for ((subCategoryIndex, subCategory) in subCategoryList.withIndex()){
                            val subCategoryName: String? = subCategory.sub_category_name
                            if(BuildConfig.DEBUG) Log.d(TAG, "subCategoryName: $subCategoryName")
                            val items: List<Item>? = subCategory.items
                            val listRowAdapter: ArrayObjectAdapter = ArrayObjectAdapter(CustomCardPresenter())
                            if(items!=null && items.isNotEmpty()){
                                for(item in items){
                                    if(BuildConfig.DEBUG) Log.i(TAG, "movieName: ${item.name}")
                                    listRowAdapter.add(item)
                                }
                                val header: HeaderItem = HeaderItem(0, subCategoryName)
                                rowsAdapter.add(ListRow(header, listRowAdapter))
                            }
                        }
                    }
                    val fragmentAdapter: ArrayObjectAdapter = ArrayObjectAdapter()
                    val rowsSupportFragment: RowsSupportFragment = RowsSupportFragment()
                    rowsSupportFragment.adapter = rowsAdapter
                    fragmentAdapter.add(rowsSupportFragment)
                    val header: HeaderItem = HeaderItem(0, categoryName)
                    mainAdapter.add(ListRow(header, fragmentAdapter))
                }
            }
        }
        adapter = mainAdapter
        if(context!=null){
            //左側 HeaderSupportFragment 的背景
            brandColor = ContextCompat.getColor(context!!, R.color.header_background)
            //右側右上方 icon
            badgeDrawable = ContextCompat.getDrawable(context!!, R.drawable.vscinemas_logo)
        }
    }

}