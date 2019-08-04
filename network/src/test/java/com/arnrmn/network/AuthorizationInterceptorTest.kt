package com.arnrmn.network

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.apollographql.apollo.request.RequestHeaders
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AuthorizationInterceptorTest {
    private val keyProvider: GithubKeyProvider = mock()
    private val request: ApolloInterceptor.InterceptorRequest = mock()
    private val newRequest: ApolloInterceptor.InterceptorRequest = mock()
    private val builder: ApolloInterceptor.InterceptorRequest.Builder = mock()
    private val chain: ApolloInterceptorChain = mock()
    private val headerCaptor = argumentCaptor<RequestHeaders>()
    private lateinit var interceptor: AuthorizationInterceptor
    private val key = "test key"

    @Before
    fun setup() {
        given(keyProvider.key).willReturn(key)
        given(request.toBuilder()).willReturn(builder)
        given(builder.requestHeaders(headerCaptor.capture())).willReturn(builder)
        given(builder.build()).willReturn(newRequest)
        interceptor = AuthorizationInterceptor(keyProvider)
    }

    @Test
    fun `when intercepted then authorization header is attached`() {
        interceptor.interceptAsync(request, chain, mock(), mock())

        Assert.assertTrue(headerCaptor.firstValue.hasHeader("Authorization"))
    }

    @Test
    fun `when intercepted then authorization header is formatter correctly`() {
        val expected = "bearer $key"
        interceptor.interceptAsync(request, chain, mock(), mock())

        val actual = headerCaptor.firstValue.headerValue("Authorization")

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `when intercepted then authorized request is sent to chain`() {
        interceptor.interceptAsync(request, chain, mock(), mock())

        verify(chain).proceedAsync(eq(newRequest), any(), any())
    }

    @Test
    fun `when intercepted then old request is not sent to chain`() {
        interceptor.interceptAsync(request, chain, mock(), mock())

        verify(chain, never()).proceedAsync(eq(request), any(), any())
    }
}