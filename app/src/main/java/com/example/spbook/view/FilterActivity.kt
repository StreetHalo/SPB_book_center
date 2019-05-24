package com.example.spbook.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
////
        filter_books.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                setStoreGroup(true)
            else
                setStoreGroup(false)
        }

        filter_books_cafe.setOnClickListener(View.OnClickListener {
            filter_books_cafe.isChecked = !filter_books_cafe.isChecked
        })

        filter_books_coworking.setOnClickListener(View.OnClickListener {
            filter_books_coworking.isChecked = !filter_books_coworking.isChecked
        })

        filter_books_wifi.setOnClickListener(View.OnClickListener {
            filter_books_wifi.isChecked = !filter_books_wifi.isChecked
        })

////
        filter_lib.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                setLibGroup(true)
            else
                setLibGroup(false)
        }

        filter_lib_coworking.setOnClickListener(View.OnClickListener {
            filter_lib_coworking.isChecked = !filter_lib_coworking.isChecked
        })

        filter_lib_printer.setOnClickListener(View.OnClickListener {
            filter_lib_printer.isChecked = !filter_lib_printer.isChecked
        })

        filter_lib_wifi.setOnClickListener(View.OnClickListener {
            filter_lib_wifi.isChecked = !filter_lib_wifi.isChecked
        })
    }


    override fun onResume() {
        super.onResume()
        filter_publ.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_GROUP, true)
        filter_publ_your_book.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_YOUR_BOOK, false)
        filter_publ_store.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_STORE, false)
        filter_publ_online_store.isChecked = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_ONLINE_STORE, false)

        filter_books.isChecked = sharedPreferences.getBoolean(BOOK_KEY_FILTER_GROUP,true)
        filter_books_wifi.isChecked = sharedPreferences.getBoolean(BOOK_KEY_FILTER_WIFI, false)
        filter_books_cafe.isChecked = sharedPreferences.getBoolean(BOOK_KEY_FILTER_CAFE, false)
        filter_books_coworking.isChecked = sharedPreferences.getBoolean(BOOK_KEY_FILTER_COWORKING, false)

        filter_lib.isChecked = sharedPreferences.getBoolean(LIB_KEY_FILTER_GROUP,true)
        filter_lib_wifi.isChecked = sharedPreferences.getBoolean(LIB_KEY_FILTER_WIFI, false)
        filter_lib_printer.isChecked = sharedPreferences.getBoolean(LIB_KEY_FILTER_PRINT, false)
        filter_lib_coworking.isChecked = sharedPreferences.getBoolean(LIB_KEY_FILTER_COWORKING, false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        val editor = sharedPreferences.edit()
        Log.d("FILTER", "ON PAUSE")
        editor.putBoolean(PUBLISH_KEY_FILTER_GROUP, filter_publ.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_YOUR_BOOK, filter_publ_your_book.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_STORE, filter_publ_store.isChecked)
        editor.putBoolean(PUBLISH_KEY_FILTER_ONLINE_STORE, filter_publ_online_store.isChecked)

        editor.putBoolean(BOOK_KEY_FILTER_COWORKING,filter_books_coworking.isChecked)
        editor.putBoolean(BOOK_KEY_FILTER_CAFE,filter_books_cafe.isChecked)
        editor.putBoolean(BOOK_KEY_FILTER_WIFI,filter_books_wifi.isChecked)
        editor.putBoolean(BOOK_KEY_FILTER_GROUP,filter_books.isChecked)

        editor.putBoolean(LIB_KEY_FILTER_GROUP,filter_lib.isChecked)
        editor.putBoolean(LIB_KEY_FILTER_WIFI,filter_lib_wifi.isChecked)
        editor.putBoolean(LIB_KEY_FILTER_PRINT,filter_lib_printer.isChecked)
        editor.putBoolean(LIB_KEY_FILTER_COWORKING,filter_lib_coworking.isChecked)

        editor.apply()
    }

    private fun setPublishGroup( isEnable: Boolean){
        filter_publ.isChecked = isEnable
        filter_publ_your_book.isEnabled = isEnable
        filter_publ_store.isEnabled = isEnable
        filter_publ_online_store.isEnabled = isEnable
    }


    private fun setLibGroup(isEnable: Boolean) {
        filter_lib.isChecked = isEnable
        filter_lib_wifi.isEnabled = isEnable
        filter_lib_printer.isEnabled = isEnable
        filter_lib_coworking.isEnabled = isEnable

    }

    private fun setStoreGroup(isEnable: Boolean) {
        filter_books.isChecked = isEnable
        filter_books_coworking.isEnabled = isEnable
        filter_books_cafe.isEnabled = isEnable
        filter_books_wifi.isEnabled = isEnable
    }
}
