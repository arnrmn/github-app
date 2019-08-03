package com.arnrmn.githubapp.main

import android.os.Bundle
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.utils.android.BaseActivity
import com.arnrmn.githubapp.utils.android.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private val viewModel by viewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
