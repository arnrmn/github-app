package com.arnrmn.network.repositories

import com.arnrmn.usecase.repositories.RepositoriesProvider

class NetworkRepositoriesProvider(
    delegate: RepositoriesProvider = ApolloRepositoriesProvider()
) : RepositoriesProvider by delegate
