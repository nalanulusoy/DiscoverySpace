package com.nalan.discoveryspace.data.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nalan.discoveryspace.R

import dagger.android.AndroidInjection
import com.nalan.discoveryspace.data.NavigationController
import javax.inject.Inject



class MainActivity : AppCompatActivity() {

    @Inject
   lateinit var  navigationController: NavigationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigationController.navigateToHomeFragment()
        }

    }

}
