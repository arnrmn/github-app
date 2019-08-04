package com.arnrmn.network.repositories

import com.apollographql.apollo.api.Response
import com.arnrmn.githubapp.RepositoriesQuery
import com.arnrmn.usecase.repositories.Repository

internal object RepositoryResponseMapper :
    RepositoriesMapper<Response<RepositoriesQuery.Data>> {

    override fun map(data: Response<RepositoriesQuery.Data>): List<Repository> {
        return data.data()?.organization()?.repositories()?.nodes()
            ?.map { repoResponse ->
                Repository(
                    repoResponse.name(),
                    repoResponse.url().toString()
                )
            }
            ?: emptyList()
    }
}