package com.arnrmn.githubapp.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arnrmn.usecase.repositories.Repository
import com.arnrmn.usecase.repositories.ToptalRepositoriesUseCase
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RepositoriesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val useCase: ToptalRepositoriesUseCase = mock()

    @Test
    fun `given useCase succeeds when viewModel init then stores repositories`() {
        val repos = listOf<Repository>(mock())
        given { useCase.getRepositories() }.willReturn(Single.just(repos))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.repositories
            .test()
            .assertValue(repos)
    }

    @Test
    fun `given useCase succeeds when viewModel init then no error is stored`() {
        val repos = listOf<Repository>(mock())
        given { useCase.getRepositories() }.willReturn(Single.just(repos))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.error
            .test()
            .assertNoValue()
    }

    @Test
    fun `given useCase fails when viewModel init then stores error`() {
        val error = Throwable("Test error")
        given { useCase.getRepositories() }.willReturn(Single.error(error))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.error
            .test()
            .assertValue(error.message)
    }

    @Test
    fun `given useCase fails when viewModel init then stores no repos`() {
        val error = Throwable("Test error")
        given { useCase.getRepositories() }.willReturn(Single.error(error))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.repositories
            .test()
            .assertNoValue()
    }

    @Test
    fun `given useCase succeeds when it failed before then stores repos`() {
        val error = Throwable("Test error")
        val repos = listOf<Repository>(mock())
        given { useCase.getRepositories() }.willReturn(Single.error(error), Single.just(repos))

        val viewModel = RepositoriesViewModel(useCase)
        viewModel.onRefreshRequested()

        viewModel.repositories
            .test()
            .assertValue(repos)
    }

    @Test
    fun `given is loading then isLoading is set to true`() {
        given { useCase.getRepositories() }.willReturn(Single.never())

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.isLoading
            .test()
            .assertValue(true)
    }

    @Test
    fun `given operation finished then isLoading is set to false`() {
        given { useCase.getRepositories() }.willReturn(Single.just(emptyList()))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.isLoading
            .test()
            .assertValue(false)
    }

    @Test
    fun `given operation failed then isLoading is set to false`() {
        given { useCase.getRepositories() }.willReturn(Single.error(Throwable()))

        val viewModel = RepositoriesViewModel(useCase)

        viewModel.isLoading
            .test()
            .assertValue(false)
    }

    @Test
    fun `when repo is selected then it is stored`() {
        val repo = mock<Repository>()
        given { useCase.getRepositories() }.willReturn(Single.just(emptyList()))

        val viewModel = RepositoriesViewModel(useCase)
        viewModel.onRepositorySelected(repo)

        viewModel.selectedRepository
            .test()
            .assertValue(repo)
    }

    @Test
    fun `given operation not finished when onCleared is called then operation is disposed`() {
        var isDisposed = false
        val result = Single.never<List<Repository>>().doOnDispose { isDisposed = true }
        given { useCase.getRepositories() }.willReturn(result)

        val viewModel = RepositoriesViewModel(useCase)
        viewModel.onCleared()

        Assert.assertTrue(isDisposed)
    }
}