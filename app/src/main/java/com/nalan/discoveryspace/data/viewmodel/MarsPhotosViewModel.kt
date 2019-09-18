package com.nalan.discoveryspace.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.data.model.Status
import io.reactivex.disposables.CompositeDisposable

class MarsPhotosViewModel  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val marsDataSourceFactory =MarsPhotosDataSourceFactory ()

    private val pagedListConfig = PagedList.Config.Builder().apply {
        setEnablePlaceholders(true)
        setInitialLoadSizeHint(10)
        setPageSize(10)
    }.build()

    private val marsPhotos = LivePagedListBuilder(marsDataSourceFactory, pagedListConfig).build()

    private val marsPhotosLoadStatus = Transformations.switchMap(marsDataSourceFactory.getLiveData(),MarsPhotosDataSource::getProgressLiveStatus)

    fun getmarsPhotosLoadStatus() : LiveData<Status> = marsPhotosLoadStatus

    fun getmarsPhotosLiveData() : LiveData<PagedList<Space.Photos>> = marsPhotos

    fun refresh() {
        marsDataSourceFactory.getLiveData().value?.invalidate()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}