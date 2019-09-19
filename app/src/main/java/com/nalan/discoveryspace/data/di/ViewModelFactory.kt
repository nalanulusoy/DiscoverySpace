package com.nalan.discoveryspace.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nalan.discoveryspace.data.viewmodel.MarsPhotosViewModel
import javax.inject.Inject

//
// Created by Nalan Ulusoy on 2019-09-19.
//
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