package com.nalan.discoveryspace.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory
    @Inject
    constructor() : ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MarsPhotosViewModel::class.java)) {
                return MarsPhotosViewModel() as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }

