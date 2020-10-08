package com.cindy.myfirstandroidtvapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processListener()

    }

    fun processListener(){
        vBrowseSupportFragment.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {

                val intent: Intent = Intent()
                intent.setClass(this@MainActivity, BrowseSupportFragmentActivity::class.java)
                startActivity(intent)

            }
        })
        vTopTabNavigation.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {

                val intent: Intent = Intent()
                intent.setClass(this@MainActivity, TopTabNavigationActivity::class.java)
                startActivity(intent)

            }
        })
    }

}