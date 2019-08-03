package com.arnrmn.githubapp.utils.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected inline fun <reified T : ViewModel> viewModel(factory: ViewModelFactory<T>): Lazy<T> {
        return lazy { ViewModelProviders.of(this, factory).get(T::class.java) }
    }
}