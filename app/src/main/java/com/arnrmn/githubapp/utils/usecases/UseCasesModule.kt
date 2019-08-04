package com.arnrmn.githubapp.utils.usecases

import com.arnrmn.network.repositories.NetworkRepositoriesProvider
import com.arnrmn.usecase.repositories.RepositoriesProvider
import com.arnrmn.usecase.repositories.ToptalRepositoriesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideReposProvider() = NetworkRepositoriesProvider()

        @Provides
        @JvmStatic
        fun provideRepositoriesUseCase(provider: RepositoriesProvider) = ToptalRepositoriesUseCase(provider)
    }
}