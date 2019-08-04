package com.arnrmn.network.repositories

import com.arnrmn.usecase.repositories.RepositoriesProvider
import com.arnrmn.usecase.repositories.Repository
import io.reactivex.Single

class NetworkRepositoriesProvider(private val delegate: RepositoriesProvider = ApolloRepositoriesProvider()) :
    RepositoriesProvider {
    override fun getRepositories(organization: String, limit: Int): Single<List<Repository>> {
        return delegate.getRepositories(organization, limit)
    }
}