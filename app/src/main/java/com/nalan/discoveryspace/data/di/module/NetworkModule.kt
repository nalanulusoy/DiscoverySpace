package com.nalan.discoveryspace.data.di.module



import com.google.gson.GsonBuilder
import com.nalan.discoveryspace.data.data.ApiClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


 @Module
 object NetworkModule {
    val BASE_URL = "https://api.nasa.gov/"

     @Provides
     @Reusable
     @JvmStatic
     internal fun providePostApi(retrofit: Retrofit): ApiClient {
         return retrofit.create(ApiClient::class.java)
     }

     @Provides
     @Reusable
     @JvmStatic
     internal fun provideRetrofitInterface(): Retrofit {
         return Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
             .build()
     }

}


