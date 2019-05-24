package com.example.spbook.view

import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceActivity
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.spbook.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import android.widget.LinearLayout
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adsElement = Element()
       adsElement.title = "Политика конфиденциальности"

        val aboutPage = AboutPage(this)
            .isRTL(false)
            .setDescription("Книжный Петербург")
            .setImage(com.example.spbook.R.drawable.logo)
            .addEmail("elmehdi.sakout@gmail.com")
            .addWebsite("https://spblib.ru/")
            .addInstagram("medyo80")
            .addItem(adsElement)

            .create()

        setContentView(R.layout.activity_about)
        about_layout.addView(aboutPage, 1)
        toolbar_about.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_about.title = " "
        setSupportActionBar(toolbar_about)



    }


    internal fun getCopyRightsElement(): Element {
        val copyRightsElement = Element()
        val copyrights = "copy"
        copyRightsElement.title = copyrights
/*
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right)
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color)
        copyRightsElement.setIconNightTint(android.R.color.white)
        copyRightsElement.setGravity(Gravity.CENTER)
*/


      /*  copyRightsElement.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this,
                copyrights,
                Toast.LENGTH_SHORT
            ).show()
        })*/
        return copyRightsElement
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun test(){

    }
}
