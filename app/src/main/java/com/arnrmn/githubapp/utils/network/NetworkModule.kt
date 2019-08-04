package com.arnrmn.githubapp.utils.network

import com.apollographql.apollo.ApolloClient
import com.arnrmn.githubapp.type.CustomType
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Module
    companion object {
        private const val BASE_URL = "https://api.github.com/graphql"

        @JvmStatic
        @Provides
        fun provideApolloClient(): ApolloClient {
            return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .addApplicationInterceptor(AuthorizationInterceptor)
                .addCustomTypeAdapter(CustomType.URI, UriAdapter)
                .build()
        }
    }
}