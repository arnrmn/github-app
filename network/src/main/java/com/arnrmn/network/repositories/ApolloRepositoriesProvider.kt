package com.arnrmn.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.arnrmn.githubapp.RepositoriesQuery
import com.arnrmn.network.Api
import com.arnrmn.usecase.repositories.RepositoriesProvider
import com.arnrmn.usecase.repositories.Repository
import io.reactivex.Single

internal class ApolloRepositoriesProvider(
    private val client: ApolloClient = Api.client,
    private val mapper: RepositoriesMapper<Response<RepositoriesQuery.Data>> = RepositoryResponseMapper
) : RepositoriesProvider {

    override fun getRepositories(organization: String, limit: Int): Single<List<Repository>> {
        return Rx2Apollo.from(client.query(RepositoriesQuery(organization, Input.optional(limit))))
            .map { response -> mapper.map(response) }
            .firstOrError()
    }
}