package com.nalan.discoveryspace.data.di.module

import com.nalan.discoveryspace.data.view.MarsPhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMarsPhotosFragment(): MarsPhotosFragment


}