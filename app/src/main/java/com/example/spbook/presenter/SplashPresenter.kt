package com.example.spbook.presenter

import android.util.Log
import com.example.spbook.entities.POJO.AuthorsQuote
import com.example.spbook.SPLASH_DELAY
import com.example.spbook.interactors.AuthorsInteractor
import com.example.spbook.interactors.PlacesInteractor
import com.example.spbook.view.SplashViewInterface
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


open class SplashPresenter(
    private val placesInteractor: PlacesInteractor,
    private val authorsInteractor: AuthorsInteractor
)

{

    val TAG = "Presenter"
    private var compositeDisposable = CompositeDisposable()
    private lateinit var  splashViewInterface: SplashViewInterface
    private var isBook = false
    private var isPublish = false
    private var isLib = false



    fun attach(splashViewInterface: SplashViewInterface){
        this.splashViewInterface = splashViewInterface
        getAuthorText()
    }


   private fun setAuthorText(author:AuthorsQuote){
       splashViewInterface.setText(author.author,author.quote)
    }

    private fun getAuthorText(){

        val b=  authorsInteractor.getListAuthors()

            .subscribe{it ->
                run {
                    setAuthorText(it.random())
                }
            }

      compositeDisposable.add(b)

    }

    fun getPlaces(){
        val publish= this.placesInteractor.getListPublish()
          //  .delay(SPLASH_DELAY,TimeUnit.MILLISECONDS)
            .subscribe {it ->
                splashViewInterface.setPublishList(it)
                isPublish = true
                startMapActivity()
            }

        val bookStores= this.placesInteractor.getListBookStores()
            .subscribe {it ->
                splashViewInterface.setBookStoresList(it)
                isBook = true
                startMapActivity()

            }

        val libs= this.placesInteractor.getListLibs()
            .subscribe {it ->
                splashViewInterface.setLibsList(it)
                isLib = true
                startMapActivity()

            }
        compositeDisposable.addAll(libs,bookStores,publish)
    }

    private fun startMapActivity() {
        if(isBook && isPublish && isLib) {
            isBook = false
            isPublish = false
            isLib = false
            splashViewInterface.startMapActivity()
        }
    }


    fun onDestroy(){
       compositeDisposable.dispose()
    }
}