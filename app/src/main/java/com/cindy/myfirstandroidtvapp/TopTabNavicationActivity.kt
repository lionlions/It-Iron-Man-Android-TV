package com.cindy.myfirstandroidtvapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_top_tab_navication.*
import kotlinx.android.synthetic.main.activity_top_tab_navication.view.*

class TopTabNavicationActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_tab_navication)

        processView()

    }

    fun processView(){


        tab_layout.setupWithViewPager(view_pager)

    }

}