package com.release.spbook.view

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject
import com.tbruyelle.rxpermissions2.RxPermissions
import android.widget.Toast
import com.release.spbook.*
import com.release.spbook.entities.POJO.BookStore
import com.release.spbook.entities.POJO.Library
import com.release.spbook.entities.POJO.Publish
import com.release.spbook.presenter.SplashPresenter
import io.reactivex.disposables.CompositeDisposable
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


class SplashActivity : AppCompatActivity(), SplashViewInterface {

    private lateinit var  mainIntent : Intent
    private var compositeDisposable = CompositeDisposable()

    override fun setPublishList(listPublish: ArrayList<Publish>) {
        mainIntent.putParcelableArrayListExtra(PUBLISH_LIST,listPublish)
    }

    override fun setBookStoresList(listBookStores: ArrayList<BookStore>) {
        mainIntent.putParcelableArrayListExtra(BOOK_STORES_LIST,listBookStores)
    }

    override fun setLibsList(listLibs: ArrayList<Library>) {
        mainIntent.putParcelableArrayListExtra(LIBRARIES_LIST,listLibs)
    }

    @Inject
    lateinit var presenter: SplashPresenter
    private val rxPermissions = RxPermissions(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        App.daggerMainComponent.inject(this)
        mainIntent = Intent(this, MapActivity::class.java)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)

      val permission =  rxPermissions
          .request(Manifest.permission.ACCESS_FINE_LOCATION)
            .subscribe { granted ->
                if (granted) {
               presenter.getPlaces()
                } else {
                    Toast.makeText(this, R.string.dont_have_location,Toast.LENGTH_LONG).show()
                }
            }

        compositeDisposable.add(permission)
    }

    override fun setText(author: String, citation: String) {
        this.author.text = author
        this.citation.text = citation
    }

    override fun errorFromService() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Опс!")
            .setMessage(R.string.dont_call_service)
            .setPositiveButton("Повторить") { dialog, id ->
                presenter.getPlaces()
                dialog.cancel()
            }
            .setNegativeButton("Закрыть") { dialog, id ->

                dialog.cancel()}

        builder.create()
        builder.show()
    }

    override fun startMapActivity(){
      this.startActivity(mainIntent)
     this.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        compositeDisposable.dispose()
    }
}
