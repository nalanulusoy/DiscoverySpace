package com.nalan.discoveryspace.data.data

import com.nalan.discoveryspace.data.data.model.Space
import constant.AppConstants
import io.reactivex.Observable
import retrofit2.http.GET



interface ApiClient {
    @GET(AppConstants.API_CLIENT.MARS_PHOTOS)
    fun getMarsPhotos(): Observable<Space>

}