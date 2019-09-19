package com.nalan.discoveryspace.data.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nalan.discoveryspace.data.di.ViewModelFactory
import com.nalan.discoveryspace.data.di.ViewModelKey
import com.nalan.discoveryspace.data.viewmodel.MarsPhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MarsPhotosViewModel::class)
    abstract fun bindSampleViewModel(viewModel: MarsPhotosViewModel): ViewModel

}