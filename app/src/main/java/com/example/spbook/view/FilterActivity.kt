package com.example.spbook.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.example.spbook.*

import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences :SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        toolbar_filter.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        setSupportActionBar(toolbar_filter)
        sharedPreferences = getSharedPreferences("FILTER", Context.MODE_PRIVATE)

        filter_publ.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    setPublishGroup(true)
                else
                    setPublishGroup(false)
        }

        filter_publ_your_book.setOnClickListener(View.OnClickListener {
            filter_publ_your_book.isChecked = !filter_publ_your_book.isChecked
        })

        filter_publ_store.setOnClickListener(View.OnClickListener {
            filter_publ_store.isChecked = !filter_publ_store.isChecked
        })

        filter_publ_online_store.setOnClickListener(View.OnClickListener {
            filter_publ_online_store.isChecked = !filter_publ_online_store.isChecked
        })


    }
    override fun onResume() {
        super.onResume()
        filter_publ.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_GROUP, true)
        filter_publ_your_book.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_YOUR_BOOK, true)
        filter_publ_store.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_STORE, true)
        filter_publ_online_store.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_ONLINE_STORE, true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        val editor = sharedPreferences.edit()


        editor.putBoolean(PUBLISH_KEY_FILTER_GROUP, filter_publ.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_YOUR_BOOK, filter_publ_your_book.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_STORE, filter_publ_store.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_ONLINE_STORE, filter_publ_online_store.isChecked)
        editor.apply()

    }

    private fun setPublishGroup( isEnable: Boolean){
        filter_publ.isChecked = isEnable
        filter_publ_your_book.isEnabled = isEnable
        filter_publ_store.isEnabled = isEnable
        filter_publ_online_store.isEnabled = isEnable
    }
}
