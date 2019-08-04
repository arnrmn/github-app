package com.arnrmn.network.repositories

import com.apollographql.apollo.api.Response
import com.arnrmn.network.RepositoriesQuery
import com.arnrmn.usecase.repositories.Repository

internal object RepositoryResponseMapper :
    RepositoriesMapper<Response<RepositoriesQuery.Data>> {

    override fun map(data: Response<RepositoriesQuery.Data>): List<Repository> {
        return data.data()?.organization()?.repositories()?.nodes()
            ?.map { repoResponse ->
                Repository(
                    name = repoResponse.name(),
                    url = repoResponse.url().toString(),
                    openIssues = repoResponse.openIssues().totalCount(),
                    closedIssues = repoResponse.closedIssues().totalCount(),
                    openPullRequests = repoResponse.openPullRequests().totalCount(),
                    closedPullRequests = repoResponse.closedPullRequests().totalCount()
                )
            }
            ?: emptyList()
    }
}