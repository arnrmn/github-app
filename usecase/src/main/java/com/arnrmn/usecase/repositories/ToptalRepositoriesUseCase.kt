package com.arnrmn.usecase.repositories

import io.reactivex.Single

class ToptalRepositoriesUseCase(
    private val provider: RepositoriesProvider
) {
    fun getRepositories(): Single<List<Repository>> {
        return provider.getRepositories(ORGANIZATION, LIMIT)
    }

    companion object {
        private const val ORGANIZATION = "toptal"
        private const val LIMIT = 100
    }
}