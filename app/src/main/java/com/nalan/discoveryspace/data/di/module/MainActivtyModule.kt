package com.nalan.discoveryspace.data.di.module

import com.nalan.discoveryspace.data.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

    @Module
    abstract  class MainActivityModule {
        @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
        internal abstract fun contributeMainActivity(): MainActivity

    }
