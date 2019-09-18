package com.nalan.discoveryspace.data.di.component

import android.app.Application
import com.nalan.discoveryspace.data.SpaceApp
import com.nalan.discoveryspace.data.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,AppModule::class, FragmentBuildersModule::class, MainActivityModule::class, ViewModelModule::class
    ]
)

 interface AppComponent  {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: SpaceApp)
}

