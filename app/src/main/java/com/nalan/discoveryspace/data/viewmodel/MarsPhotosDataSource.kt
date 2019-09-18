package com.nalan.discoveryspace.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.data.model.Status
import com.nalan.discoveryspace.data.util.UtilApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MarsPhotosDataSource : PageKeyedDataSource<Int, Space.Photos>() {
    var pageNumber = 0
    private val progressLiveStatus = MutableLiveData<Status>()
    fun getProgressLiveStatus() = progressLiveStatus
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Space.Photos>) {
        Log.e("source", "after")

        val currentPage = params.key
        UtilApi.getAPIService().getMarsPhotos(params.key, 1000)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Space> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(mod: Space) {

                    val nextKey = currentPage + 1
                    callback.onResult(
                        mod.getPhotos()!!, nextKey
                    )

                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR", e.message)
                }

                override fun onComplete() {

                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Space.Photos>) {

    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Space.Photos>
    ) {
       // networkState.postValue()
        UtilApi.getAPIService().getMarsPhotos(pageNumber, 1000)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Space> {
                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: Space) {
                    TODO("not implemented")
                    if (pageNumber == 0) {
                        callback.onResult(
                            t.getPhotos()!!,
                            null, pageNumber + 1
                        )

                    }


                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR", e.message)
                   // networkState.postValue(NetworkState(Status.FAILED, e.message))
                }

                override fun onComplete() {


                }
            })

    }




}



