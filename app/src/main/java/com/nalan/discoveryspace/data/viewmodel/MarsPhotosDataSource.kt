package com.nalan.discoveryspace.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nalan.discoveryspace.data.data.ApiClient
import com.nalan.discoveryspace.data.data.ApiService
import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.data.model.Status
import io.reactivex.Completable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarsPhotosDataSource(open val apiService: ApiService,private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Space.Photos>() {
    var pageNumber = 0
    var state: MutableLiveData<Status> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Space.Photos>) {
        Log.e("source", "after")
        updateState(Status.LOADING)
        compositeDisposable.add(
            apiService.getPhotos(params.key, 1000)
                .subscribe(
                    { response ->
                        updateState(Status.SUCCESS)
                        callback.onResult(response.getPhotos()!!,
                            params.key + 1
                        )
                    },
                    {
                        updateState(Status.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Space.Photos>) {

    }

    private fun updateState(state: Status) {
        this.state.postValue(state)
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Space.Photos>
    ) {
        updateState(Status.LOADING)
        compositeDisposable.add(
            apiService.getPhotos(pageNumber, 1000)
                .subscribe(
                    { response ->
                        updateState(Status.SUCCESS)
                        callback.onResult(response.getPhotos()!!,
                            null,
                            pageNumber+1
                        )
                    },
                    {
                        updateState(Status.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )

    }





    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}



