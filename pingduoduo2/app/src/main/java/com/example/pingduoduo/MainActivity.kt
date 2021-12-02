package com.example.pingduoduo

import android.os.Bundle
import android.text.TextUtils
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mStrs =
        arrayOf("kk", "kk", "wskx", "wksx")
    private val mSearchView: SearchView? = null
    private val lListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.setTextFilterEnabled(true)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            //// 当点击搜索按钮时触发该方法
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else{
                    listView.clearTextFilter();
                }
                return false;


            }

        })

    }
}
