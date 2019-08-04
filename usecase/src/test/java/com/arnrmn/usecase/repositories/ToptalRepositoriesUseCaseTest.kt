package com.arnrmn.usecase.repositories

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ToptalRepositoriesUseCaseTest {
    private val provider: RepositoriesProvider = mock()
    private lateinit var useCase: ToptalRepositoriesUseCase
    private val repos = listOf<Repository>(mock())

    @Before
    fun setup() {
        given(provider.getRepositories(any(), any())).willReturn(Single.just(repos))
        useCase = ToptalRepositoriesUseCase(provider)
    }

    @Test
    fun `when subscribed then uses TopTal as Organization`() {
        val organization = "toptal"

        useCase.getRepositories()
            .test()
            .assertOf { verify(provider).getRepositories(eq(organization), any()) }
    }

    @Test
    fun `when subscribed then uses 100 as limit`() {
        val limit = 100

        useCase.getRepositories()
            .test()
            .assertOf { verify(provider).getRepositories(any(), eq(limit)) }
    }

    @Test
    fun `given success when subscribed then returns unmodified list`() {
        useCase.getRepositories()
            .test()
            .assertValue(repos)
    }

    @Test
    fun `given error when subscribed then returns unmodified error`() {
        val error = Throwable("Test error")
        given(provider.getRepositories(any(), any())).willReturn(Single.error(error))

        useCase.getRepositories()
            .test()
            .assertError(error)
    }

}