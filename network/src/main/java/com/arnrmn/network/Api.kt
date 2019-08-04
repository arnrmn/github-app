package com.arnrmn.network

import com.apollographql.apollo.ApolloClient
import com.arnrmn.network.type.CustomType

internal object Api {
    private const val BASE_URL = "https://api.github.com/graphql"

    val client: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(BASE_URL)
            .addApplicationInterceptor(AuthorizationInterceptor())
            .addCustomTypeAdapter(CustomType.URI, UriAdapter)
            .build()
    }
}