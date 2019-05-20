package com.example.spbook.presenter

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.example.spbook.POJO.AuthorsQuote
import com.example.spbook.SPLASH_DELAY
import com.example.spbook.interactors.AuthorsInteractor
import com.example.spbook.interactors.PlacesInteractor
import com.example.spbook.view.SplashViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    //    compositeDisposable.add(b)

    }

    fun getPlaces(){
        val publish= this.placesInteractor.getListPublish()
            .delay(SPLASH_DELAY,TimeUnit.MILLISECONDS)
            .subscribe {it ->
                Log.d(TAG,"SET PUBLISH LIST ${it.size}")
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


      //  compositeDisposable.addAll(publish,bookStores)

    }

    private fun startMapActivity() {
        if(isBook && isPublish) {
            isBook = false
            isPublish = false
            splashViewInterface.startMapActivity()
        }
    }


    fun onDestroy(){
     //   compositeDisposable.dispose()
    }
}