package com.example.spbook.view

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject
import com.tbruyelle.rxpermissions2.RxPermissions
import android.widget.Toast
import com.example.spbook.*
import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Library
import com.example.spbook.entities.POJO.Publish
import com.example.spbook.presenter.SplashPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


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
