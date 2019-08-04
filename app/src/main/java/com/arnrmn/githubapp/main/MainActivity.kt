package com.arnrmn.githubapp.main

import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.rx2.Rx2Apollo
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.RepositoriesQuery
import com.arnrmn.githubapp.utils.android.BaseActivity
import com.arnrmn.githubapp.utils.android.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    @Inject
    lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val query = RepositoriesQuery("toptal", Input.optional(100))
        val call = apolloClient.query(query)
        Rx2Apollo.from(call)
            .subscribe({ response -> Log.d("apollo", response.toString()) }, { error -> error.printStackTrace() })

    }
}
