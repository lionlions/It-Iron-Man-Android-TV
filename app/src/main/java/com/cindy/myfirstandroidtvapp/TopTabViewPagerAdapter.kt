package com.cindy.myfirstandroidtvapp

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.cindy.myfirstandroidtvapp.CustomView.CustomCardPresenter
import com.cindy.myfirstandroidtvapp.Model.Data
import com.cindy.myfirstandroidtvapp.Model.Item
import com.cindy.myfirstandroidtvapp.Model.SubCategory

class TopTabViewPagerAdapter(fragmentManager: FragmentManager, behavior: Int, var mMovieListData: List<Data>? = null): FragmentStatePagerAdapter(fragmentManager, behavior) {

    private val TAG: String = javaClass.simpleName

    override fun getItem(position: Int): Fragment {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== getItem =====")

        mMovieListData?.run {
            val category: Data = mMovieListData!![position]
            val subCategoryList: List<SubCategory>? = category.sub_categories
            if (subCategoryList != null && subCategoryList.isNotEmpty()) {
                val rowsSupportFragment: RowsSupportFragment = RowsSupportFragment()
                val rowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter())
                for ((subCategoryIndex, subCategory) in subCategoryList.withIndex()) {
                    val subCategoryName: String? = subCategory.sub_category_name
                    if (BuildConfig.DEBUG) Log.d(TAG, "subCategoryName: $subCategoryName")
                    val items: List<Item>? = subCategory.items
                    val listRowAdapter: ArrayObjectAdapter =
                        ArrayObjectAdapter(CustomCardPresenter())
                    if (items != null && items.isNotEmpty()) {
                        for (item in items) {
                            if (BuildConfig.DEBUG) Log.i(TAG, "movieName: ${item.name}")
                            listRowAdapter.add(item)
                        }
                        val header: HeaderItem = HeaderItem(0, subCategoryName)
                        rowsAdapter.add(ListRow(header, listRowAdapter))
                    }
                }
                rowsSupportFragment.adapter = rowsAdapter
                return rowsSupportFragment
            }
        }
        return RowsSupportFragment()
    }

    override fun getCount(): Int {
        if(BuildConfig.DEBUG) Log.v(TAG, "===== getCount =====")
        return if(mMovieListData!=null) mMovieListData!!.size else 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val category: Data = mMovieListData!![position]
        val categoryName: String? = category.category_name
        if (BuildConfig.DEBUG) Log.w(TAG, "categoryName: $categoryName")
        return categoryName
    }
}