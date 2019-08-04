package com.arnrmn.network.repositories

import com.arnrmn.usecase.repositories.Repository

interface RepositoriesMapper<T> {
    fun map(data: T): List<Repository>
}