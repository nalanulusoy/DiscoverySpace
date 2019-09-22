package com.nalan.discoveryspace.data.di.component

import com.nalan.discoveryspace.data.SpaceApp
import com.nalan.discoveryspace.data.data.ApiService
import com.nalan.discoveryspace.data.di.module.*

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,AppModule::class, FragmentBuildersModule::class, MainActivityModule::class, ViewModelModule::class
    ]
)

 interface AppComponent  {

    fun inject(application: SpaceApp)
    fun inject(apiService:ApiService)

}

