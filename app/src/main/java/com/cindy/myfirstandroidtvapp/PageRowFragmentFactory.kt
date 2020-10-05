package com.cindy.myfirstandroidtvapp

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.cindy.myfirstandroidtvapp.model.Data
import com.cindy.myfirstandroidtvapp.model.Item
import com.cindy.myfirstandroidtvapp.model.SubCategory

class PageRowFragmentFactory: BrowseSupportFragment.FragmentFactory<Fragment?>() {

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
                        val rowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter())
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
                        rowsSupportFragment.adapter = rowsAdapter
                        return  rowsSupportFragment
                    }
                }
            }
        }
        return null

    }

}