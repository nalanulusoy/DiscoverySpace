package com.nalan.discoveryspace.data.data

import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.constant.AppConstants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiClient {
    @GET(AppConstants.API_CLIENT.MARS_PHOTOS)
    fun getMarsPhotos(@Query("page") page: Int,@Query("sol") sol: Int): Observable<Space>

}