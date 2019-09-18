package com.nalan.discoveryspace.data.di.module



import com.google.gson.GsonBuilder
import com.nalan.discoveryspace.data.data.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


 @Module
 class AppModule {
    val BASE_URL = "https://api.nasa.gov/"
    @Provides
    @Singleton
    fun provideApi(): ApiService {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        // Http client
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
//                    Add standard parameters like headers here.
//                    builder.addHeader("name", "value")
                chain.proceed(builder.build())
            }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}


