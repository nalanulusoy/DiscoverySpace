package com.nalan.discoveryspace.data.data.model

import com.nalan.discoveryspace.data.data.model.Photos

class Space {
    private var photos: Array<Photos>? = null

    fun getPhotos(): Array<Photos>? {
        return photos
    }

    fun setPhotos(photos: Array<Photos>) {
        this.photos = photos
    }
}