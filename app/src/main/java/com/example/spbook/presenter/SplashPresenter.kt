package com.example.spbook.presenter

import android.util.Log
import com.example.spbook.entities.POJO.AuthorsQuote
import com.example.spbook.SPLASH_DELAY
import com.example.spbook.entities.CheckGPSSettings
import com.example.spbook.interactors.AuthorsInteractor
import com.example.spbook.interactors.PlacesInteractor
import com.example.spbook.view.SplashViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


open class SplashPresenter(
    private val placesInteractor: PlacesInteractor,
    private val authorsInteractor: AuthorsInteractor
)

{
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
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())


     .subscribe({
                  result ->
         Log.d("PRESENTER","good ${result.size}")

         splashViewInterface.setPublishList(result)
              isPublish = true
              startMapActivity()
          }, { error ->
                splashViewInterface.errorFromService()
              Log.d("PRESENTER","error ${error.message}")
          })



      val bookStores= this.placesInteractor.getListBookStores()
          .subscribe ({
                  result ->
              splashViewInterface.setBookStoresList(result)
              isBook = true
              startMapActivity()
          }, { error ->
              splashViewInterface.errorFromService()

              Log.d("PRESENTER","error ${error.message}")
          })

   val libs= this.placesInteractor.getListLibs()
       .subscribe {it ->
           Log.d("pigg","size ${it.size}")
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