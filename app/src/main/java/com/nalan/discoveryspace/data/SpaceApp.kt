package com.nalan.discoveryspace.data

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.nalan.discoveryspace.data.di.AppInjector.init
import com.nalan.discoveryspace.data.di.component.AppComponent
import com.nalan.discoveryspace.data.di.component.DaggerAppComponent
import com.nalan.discoveryspace.data.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class SpaceApp : Application(), HasActivityInjector , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorFragment: DispatchingAndroidInjector<Fragment>
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        init(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =dispatchingAndroidInjectorFragment


}