package com.nalan.discoveryspace.data.di.component

import android.app.Application
import com.nalan.discoveryspace.data.SpaceApp
import com.nalan.discoveryspace.data.di.module.AppModule
import com.nalan.discoveryspace.data.di.module.FragmentBuildersModule
import com.nalan.discoveryspace.data.di.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,FragmentBuildersModule::class,MainActivityModule::class
   ]
)
 interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    fun inject(application: SpaceApp)
}

