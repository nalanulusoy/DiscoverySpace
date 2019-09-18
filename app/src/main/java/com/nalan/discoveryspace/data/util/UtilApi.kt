package com.nalan.discoveryspace.data.util

import com.nalan.discoveryspace.data.data.ApiClient
import com.nalan.discoveryspace.data.data.ApiService.getClient


object UtilApi {
    val BASE_URL = "https://api.nasa.gov/"

    fun getAPIService(): ApiClient {
        return getClient(BASE_URL)!!.create(ApiClient::class.java!!)
    }
}