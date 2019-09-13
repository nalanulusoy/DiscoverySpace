package com.nalan.discoveryspace.data.di.module

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides


@Module
class AppModule(val application: Application) {
    @Provides

    fun provideAppContext(): Context = application
}