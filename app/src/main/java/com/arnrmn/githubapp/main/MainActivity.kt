package com.arnrmn.githubapp.main

import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.RepositoriesQuery
import com.arnrmn.githubapp.type.CustomType
import com.arnrmn.githubapp.utils.android.BaseActivity
import com.arnrmn.githubapp.utils.android.ViewModelFactory
import com.arnrmn.githubapp.utils.network.AuthorizationInterceptor
import com.arnrmn.githubapp.utils.network.UriAdapter
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
//    private val viewModel by viewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .addApplicationInterceptor(AuthorizationInterceptor)
            .addCustomTypeAdapter(CustomType.URI, UriAdapter)
            .build()
            .query(RepositoriesQuery("toptal", Input.optional(100)))
            .enqueue(object : ApolloCall.Callback<RepositoriesQuery.Data?>() {
                override fun onFailure(e: ApolloException) {
                    Log.d("Apollo", "error", e)
                }

                override fun onResponse(response: Response<RepositoriesQuery.Data?>) {
                    Log.d("Apollo", response.toString())
                }
            })
    }
}
