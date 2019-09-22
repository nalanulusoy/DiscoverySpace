package com.nalan.discoveryspace.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nalan.discoveryspace.data.data.ApiService
import com.nalan.discoveryspace.data.data.model.Space
import io.reactivex.disposables.CompositeDisposable

class MarsDataSourceFactory (open val apiService: ApiService,open val compositeDisposable: CompositeDisposable)  : DataSource.Factory<Int, Space.Photos>() {
    val marsPhotosLiveData = MutableLiveData<MarsPhotosDataSource>()

    override fun create(): DataSource<Int, Space.Photos> {
        val newsDataSource = MarsPhotosDataSource(apiService,compositeDisposable )
        marsPhotosLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}





