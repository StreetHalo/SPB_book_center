package com.release.spbook.presenter

import com.release.spbook.entities.POJO.AuthorsQuote
import com.release.spbook.interactors.AuthorsInteractor
import com.release.spbook.interactors.PlacesInteractor
import com.release.spbook.view.SplashViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


open class SplashPresenter(private val placesInteractor: PlacesInteractor, private val authorsInteractor: AuthorsInteractor)
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
         splashViewInterface.setPublishList(result)
              isPublish = true
              startMapActivity()
          }, { _ ->
                splashViewInterface.errorFromService()
          })

      val bookStores= this.placesInteractor.getListBookStores()
          .subscribe ({
                  result ->
              splashViewInterface.setBookStoresList(result)
              isBook = true
              startMapActivity()
          }, { _ ->
              splashViewInterface.errorFromService()
          })

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