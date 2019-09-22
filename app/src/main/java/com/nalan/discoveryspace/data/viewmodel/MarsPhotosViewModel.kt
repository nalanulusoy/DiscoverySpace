package com.nalan.discoveryspace.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nalan.discoveryspace.data.constant.AppConstants
import com.nalan.discoveryspace.data.data.ApiClient
import com.nalan.discoveryspace.data.data.ApiService
import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.data.model.Status
import com.nalan.discoveryspace.data.di.AppInjector
import com.nalan.discoveryspace.data.di.AppInjector.init
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MarsPhotosViewModel : ViewModel() {
    var postsLiveData: LiveData<PagedList<Space.Photos>>

    private val marsDataSourceFactory: MarsDataSourceFactory
    private val apiService = ApiService()
    private val compositeDisposable = CompositeDisposable()

    init {
        marsDataSourceFactory = MarsDataSourceFactory(apiService,compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(AppConstants.PAGİNATİON_CONFİG.PAGE_SİZE)
            .setInitialLoadSizeHint(AppConstants.PAGİNATİON_CONFİG.PAGE_SİZE * 2)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData = LivePagedListBuilder<Int, Space.Photos>(marsDataSourceFactory, config).build()

    }

    fun getState(): LiveData<Status> = Transformations.switchMap<MarsPhotosDataSource,
            Status>(marsDataSourceFactory.marsPhotosLiveData, MarsPhotosDataSource::state)

    fun retry() {
        marsDataSourceFactory.marsPhotosLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return postsLiveData.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}