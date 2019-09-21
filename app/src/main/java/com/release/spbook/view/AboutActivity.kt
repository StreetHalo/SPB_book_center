package com.release.spbook.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import com.release.spbook.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adsElement = Element()
       adsElement.title = "Политика конфиденциальности"

        val aboutPage = AboutPage(this)
            .isRTL(false)
            .setDescription("Книжный Петербург")
            .setImage(R.drawable.logo)
            .addEmail("bookspb@cgpb.ru")
            //.addWebsite("https://spblib.ru/")
            .create()

        setContentView(R.layout.activity_about)
        about_layout.addView(aboutPage, 1)
        toolbar_about.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_about.title = " "
        setSupportActionBar(toolbar_about)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}
