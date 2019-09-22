package com.nalan.discoveryspace.data.data

import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.di.component.DaggerAppComponent
import io.reactivex.Observable

import javax.inject.Inject

class ApiService {
    @Inject
    lateinit var apiClient:ApiClient



    init {
        DaggerAppComponent.create().inject(this)

    }

    fun getPhotos(page:Int,sol:Int):Observable<Space>{
        return apiClient.getMarsPhotos(page,sol)
    }

}