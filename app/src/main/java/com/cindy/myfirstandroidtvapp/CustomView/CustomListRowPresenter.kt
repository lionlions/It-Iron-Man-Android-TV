package com.cindy.myfirstandroidtvapp.CustomView

import android.content.Context
import android.util.Log
import android.view.View
import androidx.leanback.widget.*
import com.cindy.myfirstandroidtvapp.FocusType
import com.cindy.myfirstandroidtvapp.R

class CustomListRowPresenter(var mContext: Context?, var mFocusType: FocusType, focusHightlightMode: Int = FocusHighlight.ZOOM_FACTOR_MEDIUM): ListRowPresenter(focusHightlightMode) {

    private val TAG: String = javaClass.simpleName
    private var vHorizontalGridView: HorizontalGridView? = null

    override fun onBindViewHolder(
        viewHolder: Presenter.ViewHolder?,
        item: Any?,
        payloads: MutableList<Any>?
    ) {

        val view: View = viewHolder!!.view
        vHorizontalGridView = view.findViewById(androidx.leanback.R.id.row_content)

        when(mFocusType){
            FocusType.START -> {
                Log.v(TAG, "===== FocusType.START =====")
                vHorizontalGridView?.run{
                    Log.w(TAG, "vHorizontalGridView is not null")
                    windowAlignment = BaseGridView.WINDOW_ALIGN_LOW_EDGE
                    windowAlignmentOffsetPercent = 0f
                    windowAlignmentOffset = resources.getDimensionPixelSize(R.dimen.lb_browse_padding_start)
                    itemAlignmentOffsetPercent = 0f
                }
            }
        }

        super.onBindViewHolder(viewHolder, item, payloads)
    }

}

