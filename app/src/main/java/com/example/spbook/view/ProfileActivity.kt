package com.example.spbook.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.Transformers.BaseTransformer
import com.example.spbook.PLACE_INTENT_KEY
import com.example.spbook.POJO.Place
import com.example.spbook.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.tbruyelle.rxpermissions2.RxPermissions

import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.profile_content.*
import kotlinx.android.synthetic.main.render_layout.view.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var place: Place
    private val rxPermissions = RxPermissions(this)
    private lateinit var shareIcon : Drawable
    private lateinit var routeIcon :Drawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
         val arrowIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_black_24dp, null)

        profile_toolbar.navigationIcon = arrowIcon
        collaps_toolbar.title = " "
        collaps_toolbar.setCollapsedTitleTextColor(Color.BLACK)
        setSupportActionBar(profile_toolbar)
        place = intent.getParcelableExtra(PLACE_INTENT_KEY)

        setScrollImg()

        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (appBarLayout.totalScrollRange == Math.abs(verticalOffset)) {

               if ((::shareIcon.isInitialized)and(::routeIcon.isInitialized)and (arrowIcon != null)) {
                   arrowIcon!!.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                   shareIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                   routeIcon.alpha = 100
                   routeIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                }
            } else {
                if ((::shareIcon.isInitialized)and(::routeIcon.isInitialized)and (arrowIcon != null)) {
                    routeIcon.alpha = 0
                    shareIcon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
                    arrowIcon!!.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
                }
            }
        })



        service_1.setOnClickListener {
            if (place.isService1())
                Toast.makeText(baseContext, resources.getText(place.aboutService1()), Toast.LENGTH_SHORT).show()
        }

        service_2.setOnClickListener {
            if (place.isService2())
                Toast.makeText(baseContext, resources.getText(place.aboutService2()), Toast.LENGTH_SHORT).show()
        }

        service_3.setOnClickListener {
            if (place.isService3())
                Toast.makeText(baseContext, resources.getText(place.aboutService3()), Toast.LENGTH_SHORT).show()
        }

        route_button.setOnClickListener {
            goToThePlace()
        }

        url_button.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(place.placeUrl))
            startActivity(mapIntent)
        }

        phone_button.setOnClickListener {
            rxPermissions
                .request(Manifest.permission.CALL_PHONE)
                .subscribe { granted ->
                    if (granted) {
                        callPhone()
                    } else {
                        Toast.makeText(this, R.string.dont_have_location, Toast.LENGTH_LONG).show()
                    }
                }
        }

        email_button.setOnClickListener {

            sendEmail()
        }
    }

    private fun goToThePlace() {
        val navigationUri =
            Uri.parse("http://maps.google.com/maps?daddr=" + place.latitude + "," + place.longitude)
        val mapIntent = Intent(Intent.ACTION_VIEW, navigationUri)
        startActivity(mapIntent)

    }

    override fun onResume() {
        super.onResume()
        address.text = place.address
        about.text = place.about
        act_time_work.text = place.timeWork
        act_category.text = place.name

        service_1.setImageResource(place.getService1Icon())
        service_2.setImageResource(place.getService2Icon())
        service_3.setImageResource(place.getService3Icon())

        if (!place.isService1()) service_1.imageAlpha = 30
        if (!place.isService2()) service_2.imageAlpha = 30
        if (!place.isService3()) service_3.imageAlpha = 30

        if (place.telNumber == "-") {
            phone_button.isEnabled = false
            phone_button.alpha = 0.3f
        }
        if (place.placeUrl == "-") {
            url_button.isEnabled = false
            url_button.alpha = 0.3f
        }
        if (place.email == "-") {
            email_button.isEnabled = false
            email_button.alpha = 0.3f
        }
    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + place.telNumber))
        startActivity(intent)
    }

    private fun sendEmail(){
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(place.email))
        try {
            startActivity(Intent.createChooser(i, "Отправить письмо..."))
        } catch (ex: android.content.ActivityNotFoundException) {
        }
    }



    private fun setScrollImg() {


        for (name in place.images) {
            val customSlider = CustomSlider(this, R.color.pub_color)
            customSlider.image(name).scaleType = BaseSliderView.ScaleType.CenterCrop
            slider.addSlider(customSlider)
        }

        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(object : DescriptionAnimation() {});
        slider.setDuration(4000);
        slider.setCustomIndicator(custom_indicator2)

    }

    open class CustomSlider(context: Context?, private val color: Int) : BaseSliderView(context) {
        override fun getView(): View {
            val v = LayoutInflater.from(context).inflate(R.layout.render_layout, null)

            v.loading_bar.indeterminateDrawable
                .setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)


            bindEventAndShow(v, v.daimajia_slider_image)
            return v
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_toolbar, menu)

        shareIcon = menu.getItem(1).icon
        routeIcon = menu.getItem(0).icon
        routeIcon.mutate()
        shareIcon.mutate()


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_route -> goToThePlace()
            R.id.action_share -> sharePlace()
            R.drawable.ic_arrow_back_black_24dp -> finish()
        }
        return true
    }

    private fun sharePlace() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, place.placeUrl)
        startActivity(Intent.createChooser(share, "Поделиться с друзьями"))
    }
}
