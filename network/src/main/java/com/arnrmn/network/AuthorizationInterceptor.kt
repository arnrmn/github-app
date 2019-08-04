package com.arnrmn.network

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.apollographql.apollo.request.RequestHeaders
import java.util.concurrent.Executor

internal object AuthorizationInterceptor : ApolloInterceptor {
    override fun interceptAsync(
        request: ApolloInterceptor.InterceptorRequest,
        chain: ApolloInterceptorChain,
        dispatcher: Executor,
        callBack: ApolloInterceptor.CallBack
    ) {
        val authRequest = request.toBuilder()
            .requestHeaders(
                RequestHeaders.builder()
                    .addHeader("Authorization", "bearer 818102ea26b6b4245dd174bf781b178ff6724a1f")
                    .build()
            ).build()
        chain.proceedAsync(authRequest, dispatcher, callBack)
    }

    override fun dispose() {
        //Do nothing
    }
}