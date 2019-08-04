package com.arnrmn.usecase.repositories

import io.reactivex.Single

interface RepositoriesProvider {
    fun getRepositories(organization: String, limit: Int): Single<List<Repository>>
}