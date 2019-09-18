package com.nalan.discoveryspace.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nalan.discoveryspace.data.data.model.Space


class MarsPhotosDataSourceFactory(): DataSource.Factory<Int, Space.Photos>() {

    private val marsPhotosLiveData = MutableLiveData<MarsPhotosDataSource>()

    fun getLiveData() = marsPhotosLiveData

    override fun create(): MarsPhotosDataSource {
        val source = MarsPhotosDataSource()
        marsPhotosLiveData.postValue(source)
        return source
    }
}