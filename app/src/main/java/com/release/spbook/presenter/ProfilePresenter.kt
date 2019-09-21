package com.release.spbook.presenter

import com.release.spbook.entities.POJO.Place
import com.release.spbook.view.ProfileViewInterface

class ProfilePresenter {

    private lateinit var profileViewInterface: ProfileViewInterface
    private lateinit var place: Place

    fun attach(profileViewInterface: ProfileViewInterface){
        this.profileViewInterface = profileViewInterface
    }

    fun setProfile(place: Place){
        this.place = place
    }

    fun setTheme(){
        profileViewInterface.setTheme()
    }

    fun goToPlace(){

    }

    fun sharePlace(){

    }



}