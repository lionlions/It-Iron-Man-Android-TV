package com.cindy.myfirstandroidtvapp

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.cindy.myfirstandroidtvapp.CustomView.CustomCardPresenter
import com.cindy.myfirstandroidtvapp.CustomView.CustomHeaderItem
import com.cindy.myfirstandroidtvapp.CustomView.CustomListRowPresenter
import com.cindy.myfirstandroidtvapp.Model.Data
import com.cindy.myfirstandroidtvapp.Model.Item
import com.cindy.myfirstandroidtvapp.Model.SubCategory
import com.cindy.myfirstandroidtvapp.Model.BannerData

class PageRowFragmentFactory(var mContext: Context? = null): BrowseSupportFragment.FragmentFactory<Fragment?>() {

    private val TAG: String = javaClass.simpleName

    override fun createFragment(row: Any?): Fragment? {

        val row: Row = row as Row
        val headerItem: CustomHeaderItem = row.headerItem as CustomHeaderItem
        val item: Any? = headerItem.item
        item?.run {
            when(this){
                is Data -> {
                    val subCategoryList: List<SubCategory>? = sub_categories
                    if(subCategoryList!=null && subCategoryList.isNotEmpty()){
                        val rowsSupportFragment: RowsSupportFragment = RowsSupportFragment()
                        val rowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(
                            CustomListRowPresenter(
                                mContext,
                                FocusType.START,
                                focusHightlightMode = FocusHighlight.ZOOM_FACTOR_XSMALL
                            )
                        )
                        for ((subCategoryIndex, subCategory) in subCategoryList.withIndex()){
                            val subCategoryName: String? = subCategory.sub_category_name
                            if(BuildConfig.DEBUG) Log.d(TAG, "subCategoryName: $subCategoryName")
                            val items: List<Item>? = subCategory.items
                            val listRowAdapter: ArrayObjectAdapter = ArrayObjectAdapter(
                                CustomCardPresenter()
                            )
                            if(items!=null && items.isNotEmpty()){
                                for(item in items){
                                    if(BuildConfig.DEBUG) Log.i(TAG, "movieName: ${item.name}")
                                    listRowAdapter.add(item)
                                }
                                val header: HeaderItem = HeaderItem(subCategoryIndex.toLong(), subCategoryName)
                                rowsAdapter.add(ListRow(header, listRowAdapter))
                            }
                        }
                        rowsSupportFragment.adapter = rowsAdapter
                        return  rowsSupportFragment
                    }
                }
                is BannerData -> {
                    val rowsSupportFragment: RowsSupportFragment = RowsSupportFragment()
                    val rowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(
                        CustomListRowPresenter(
                            mContext,
                            isBanner = true
                    ))
                    val listRowAdapter: ArrayObjectAdapter = ArrayObjectAdapter(CustomBannerCardPresenter())
                    val items: List<String>? = banner_list
                    if(items!=null && items.isNotEmpty()){
                        for(item in items){
                            if(BuildConfig.DEBUG) Log.i(TAG, "imageUrl: $item")
                            listRowAdapter.add(item)
                        }
                        val header: HeaderItem = HeaderItem(0, "")
                        rowsAdapter.add(ListRow(header, listRowAdapter))
                        rowsSupportFragment.adapter = rowsAdapter
                        return  rowsSupportFragment
                    }
                }
            }
        }
        return null

    }

}